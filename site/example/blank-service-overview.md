# blank-service: Visión General

## ¿Qué es blank-service?

**blank-service** es el **ejemplo de referencia** del framework lg5-spring. Es un microservicio "en blanco" que puedes copiar y modificar para crear tu propio dominio de negocio.

> ⚠️ El nombre "blank" es un placeholder — cuando clones el proyecto, reemplazarás `blank` con el nombre de tu dominio (ej: `order`, `payment`, `user`).

## Stack de blank-service

| Componente | Tecnología |
|---|---|
| Framework | lg5-spring 1.0.0-alpha |
| Runtime | Spring Boot 3.5.14 + JDK 21 |
| Base de datos | PostgreSQL 17.2 (schema: `blank`) |
| Mensajería | Kafka 7.8.1 + Avro Schema Registry |
| Testing | JUnit 5 + Cucumber + Testcontainers |
| Logging | ELK (Logstash + Janino + Sleuth) |
| Mapeo | MapStruct 1.6.3 + Lombok |
| Images | JIB Docker (eclipse-temurin:21-jre) |

## Estructura de blank-service

```
blank-service/                                               # Root
│
├── blank-api/                                                # Controllers REST
│    └── com.blanksystem.blank.api.rest/
│         └── BlankController.java
│
├── blank-container/                                           # Spring Boot App
│    └── com.blanksystem.blank.container/
│         └── Application.java
│
├── blank-domain/
│    ├── blank-domain-core/                                   # Domain entities
│    │    └── com.blanksystem.blank.service.domain/
│    │        ├── entity/Blank.java
│    │        ├── valueobject/BlankId.java
│    │        ├── event/BlankCreatedEvent.java
│    │        └── exception/BlankDomainException.java
│    │
│    └── blank-application-service/                           # Use cases
│         └── com.blanksystem.blank.service.domain/
│             ├── dto/
│             ├── ports/input/service/
│             ├── ports/output/
│             ├── mapper/
│             └── BlankApplicationServiceImpl.java
│
├── blank-data-access/                                         # JPA Infrastructure
│    └── com.blanksystem.blank.service.data/
│        ├── adapter/BlankRepositoryImpl.java
│        ├── entity/BlankEntity.java                          # JPA Entity
│        ├── repository/BlankJPARepository.java
│        └── mapper/BlankDataAccessMapper.java
│
├── blank-message/
│    ├── blank-message-core/                                   # Kafka
│    │    └── com.blanksystem.blank.service.message/
│    │        ├── listener/BlankKafkaListener.java
│    │        ├── publisher/BlankEventKafkaPublisher.java
│    │        └── mapper/
│    │
│    └── blank-message-model/                                  # Avro
│         └── src/main/resources/avro/
│             └── blank.avsc
│
├── blank-acceptance-test/                                     # Cucumber BDD
├── blank-external/                                            # 3rd party adapters
└── blank-support/                                             # Aggregation, Jacoco
```

## Arquitectura de blank-service

```
┌─────────────────────────────────────────────────────────┐
│                    Hexagonal Architecture                  │
│                                                             │
│   ┌──────────────┐    ┌───────────────┐    ┌───────────┐ │
│   │    API       │    │  Domain       │    │  Data     │ │
│   │  Controller  │───▶│  Application  │───▶│  Access   │ │
│   │              │    │  Service      │    │           │ │
│   │   POST       │    │  (Use Cases)   │    │  PostgreSQL│ │
│   │   /blank     │    │               │    │           │ │
│   └──────────────┘    └───────────────┘    └───────────┘ │
│        │                    │                   │           │
│        │                    │         ┌─────────┴──────────┐│
│        │                    │         │   Outbox Pattern    ││
│        │                    ▼         │  (Event Reliability)││
│        │            ┌──────────┐       └─────────┬──────────┘│
│        │            │ Saga /   │                  │           │
│        │            │ Event     │                  ▼           │
│        ▼            │ Listener  │          ┌──────────┐       │
│   ┌──────────┐      └──────────┘          │  Kafka    │       │
│   │   3rd    │                            │  Topic:    │       │
│   │  System  │                            │  blank.1.0 │       │
│   │  (HTTP)  │◀────────────────────────── │           │       │
│   └──────────┘                            └──────────┘       │
│                                                             │
└─────────────────────────────────────────────────────────┘
```
