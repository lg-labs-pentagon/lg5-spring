# lg5-spring — Arquitectura

## Overview

lg5-spring es un **Framework BOM** para crear microservicios Spring Boot 3.5 + JDK 21 usando **Arquitectura Hexagonal (Ports-and-Adapters)** con **Domain-Driven Design (DDD)**, **Event-Driven Architecture (EDA)** y patrones distribuidos (Outbox, SAGA).

---

## Arquitectura Hexagonal (Ports-and-Adapters)

```
┌──────────────────────────────────────────────────────┐
│                    External World                      │
│   (PostgreSQL, Kafka, 3rd Party APIs, HTTP Clients)   │
└──────────────┬───────────────────────────────────────┘
               │
               ▼
┌──────────────────────────────────────────────────────┐
│              Infrastructure Layer                      │
│   (Adapters, Repository Impl, DTOs, Feign Clients)    │
│         ┌──────────┐    ┌──────────┐                   │
│         │ Adapter  │    │ Adapter  │                   │
│         │ Output   │    │ Input    │                   │
│         └──────────┘    └──────────┘                   │
└──────────────┬───────────────────────────────────────┘
               │
               ▼
┌──────────────────────────────────────────────────────┐
│                 Application Layer                      │
│   (Use Cases, ApplicationService, Sagas, Validation)   │
│         ┌──────────────┐    ┌──────────────┐          │
│         │ Input Ports   │    │Output Ports   │          │
│         │ (Service     │    │ (Repository   │          │
│         │  Interface)   │    │ Interface)    │          │
│         └──────────────┘    └──────────────┘          │
└──────────────┬───────────────────────────────────────┘
               │
               ▼
┌──────────────────────────────────────────────────────┐
│                    Domain Layer                          │
│   (Entity, ValueObject, AggregateRoot, DomainEvent)     │
│   (Pure Business Logic — NO knowledge of frameworks)     │
└──────────────────────────────────────────────────────┘
```

### Principios de la Arquitectura Hexagonal

1. **The Domain stays clean** — La capa de dominio no conoce frameworks, ni bases de datos, ni APIs externas.
2. **Dependencies point inward** — Las capas externas solo dependen de las internas, nunca al revés.
3. **Ports are interfaces** — Las interfaces (ports) viven en la Application Layer; implementaciones (adapters) viven en la Infrastructure Layer.
4. **Testability** — Los ports permiten mockear fácilmente las dependencias externas.

---

## Domain-Driven Design (DDD)

### Bounded Context: `blanksystem`

```
lg5-spring/
└── your-service/                       # Microservicio
    ├── your-domain/
    │    ├── your-domain-core/         # Bounded Context Core
    │    └── your-application-service/ # Use Cases + Sagas
    ├── your-data-access/             # Infrastructure (Database)
    ├── your-api/                      # Infrastructure (HTTP)
    ├── your-message/                  # Infrastructure (Kafka)
    ├── your-external/                 # Infrastructure (3rd Party)
    └── your-container/                # Bootstrapping
```

### Patrón AggregateRoot

```java
// cada AggregateRoot tiene una identidad única
public class Blank extends AggregateRoot<BlankId> {
    
    private static final int NUM_PARTITIONS = 3;
    private static final short REPLICATION_FACTOR = 3;
    
    public Blank(BlankId blankId) {
        super.setId(blankId);
     }

    public void validate() {
        if (getId() == null) {
            throw new BlankDomainException("Invalid blank");
        }
     }
}
```

### Patrón ValueObject

```java
// objetos inmutables con identidad
public record BlankId(UUID value) {}
```

### Patrón DomainEvent

```java
// eventos que representan cambios de estado
public record BlankCreatedEvent(Blank blank, Instant occurredOn) {}
```

### Patrón Repository (Output Port)

```java
// Interface en Application Layer
public interface BlankRepository {
    Blank createBlank(Blank blank);
    Optional<Blank> findById(UUID id);
}

// Impl en Infrastructure Layer
@Component
public class BlankRepositoryImpl implements BlankRepository {
    private final BlankJPARepository jpaRepository;
    private final BlankMapper mapper;
    
    @Override
    public Blank createBlank(Blank blank) {
        return mapper.toAggregate(
            jpaRepository.save(mapper.toEntity(blank))
        );
     }
}
```

---

## Event-Driven Architecture (EDA)

```
┌───────────┐     Database      ┌───────────┐
│           │   ────────────────► │           │
│  REST API  │   Outbox Pattern   │  Kafka     │
│  POST      │                     │  Listener  │
│  /blank     │   ┌───────────────┐ │  Event     │
│           │   │ 3rd System     │ │  Processing│
└───────────┘   └───────────────┘ └───────────┘
```

### Componentes Clave EDA

| Componente | Propósito |
|---|---|
| **KafkaProducer** | Publicar eventos a topics de Kafka con Avro |
| **KafkaConsumer** | Consumir eventos de topics de Kafka |
| **OutboxScheduler** | Garantiar eventos fiables (STARTED → COMPLETED/FAILED) |
| **SAGA** | Transacciones distribuidas con compensación |

### Outbox Pattern

```
1. Transaction inicia (DB write + Outbox row as STARTED)
2. Scheduled task lee rows STARTED/COMPLETED/FAILED
3. KafkaMessageHelper actualiza el estado a COMPLETED o FAILED
4. Servicio consume eventos de Kafka y reacciona
```

---

## SAGA Pattern

### Saga de Compensación

```java
public class CreateOrderSaga implements SagaStep<OrderContext> {
    
    @Override
    public void process(OrderContext ctx) {
        orderRepo.create(ctx.getOrder());      // Step 1: Forward
        paymentService.charge(ctx.getPayment()); // Step 2: Forward
        inventoryService.reserve(ctx.getItems()); // Step 3: Forward
     }

    @Override
    public void rollback(OrderContext ctx) {
        // Reverse order compensation
        inventoryService.release(ctx.getItems()); // Step 3: Compensate
        paymentService.refund(ctx.getPayment());   // Step 2: Compensate
        orderRepo.delete(ctx.getOrder().getId());  // Step 1: Compensate
     }
}
```

---

## Testing Pyramid

```
         / \ 
        / AT \        Cucumber BDD + Testcontainers
       /______\        (Acceptance Tests)         1 test
      /          \       ─────────────────────────
     / ___________ \     Integration Tests           4-8 tests
    / /             \ \      ────────────────────────
   / /_______________\ \   Unit Tests                 10-50 tests
  /      /     \       \     ─────────────────────────
 /______/_______\_______\
```

### Niveles de Testing

| Nivel | Framework | Propósito |
|---|---|---|
| **Unit Tests** | JUnit 5 + Mockito | Lógica pura del dominio |
| **Integration Tests** | JUnit 5 + Testcontainers | Lógica + Infraestructura real |
| **Acceptance Tests** | Cucumber 7 + Testcontainers | Comportamiento E2E E2E E2E E2E E2E E2E E2E E2E E2E E2E |

---

## Distribution de Módulos

```
lg5-spring-monorepo/
│
├── lg5-spring-parent/            # BOM Central - Gestiona todas las versiones
├── lg5-spring-starter/           # Spring Boot Starter mínimo
├── lg5-spring-api-rest/          # REST API (Spring Web, Actuator, Validation)
├── lg5-spring-data-jpa/          # Database (PostgreSQL, JPA, Liquibase)
├── lg5-spring-client/            # HTTP Client (OpenFeign)
├── lg5-spring-kafka/             # Messaging (Kafka + Avro)
│    ├── lg5-spring-kafka-config/
│    ├── lg5-spring-kafka-model/
│    ├── lg5-spring-kafka-producer/
│    └── lg5-spring-kafka-consumer/
├── lg5-spring-outbox/            # Outbox Pattern
├── lg5-jvm-saga/                 # SAGA Pattern
├── lg5-common/
│    ├── lg5-common-domain/       # DDD Primitives
│    └── lg5-common-application-service/
├── lg5-spring-logger/            # ELK Logging (Logstash, Janino)
├── lg5-jvm-utils/                # Lombok, Guava, MapStruct
├── lg5-spring-testcontainers/    # PostgreSQL, Kafka, WireMock
├── lg5-spring-integration-test/ # Test Base Classes
├── lg5-spring-acceptance-test/  # Cucumber BDD
└── lg5-jvm-unit-test/           # JUnit 5 + Mockito
```
