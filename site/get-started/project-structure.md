# Estructura de Proyecto

## Visión General

lg5-spring sigue una **arquitectura hexagonal (Ports-and-Adapters)** con **DDD** y **CQRS**. La estructura de directorios es cuidadosamente diseñada para separar responsabilidades y mantener la independencia de cada capa.

---

## Estructura de Módulos

Cada microservicio basado en lg5-spring tiene la siguiente estructura:

```
your-service/                             # Root del microservicio
│
├── your-api/                             # API REST (controllers)
│     └── com/yourcompany/your/api/rest/
│         └── YourController.java
│
├── your-container/                       # Punto de entrada Spring Boot
│     └── com/yourcompany/your/container/
│         └── Application.java
│
├── your-domain/
│     ├── your-domain-core/               # Pure domain
│     │    └── com/yourcompany/your/service/domain/
│     │         ├── entity/YourEntity.java
│     │         ├── valueobject/YourId.java
│     │         ├── event/YourEvent.java
│     │         ├── exception/YourException.java
│     │         └── yourservice/
│     │
│     └── your-application-service/       # Application layer
│         └── com/yourcompany/your/service/domain/
│             ├── dto/
│             ├── ports/input/
│             ├── ports/output/
│             └── mapper/
│
├── your-data-access/                    # Infrastructure (JPA)
│     └── com/yourcompany/your/service/data/
│         ├── adapter/YourRepositoryImpl.java
│         ├── entity/YourEntity.java
│         ├── mapper/YourDataAccessMapper.java
│         └── repository/YourJPARepository.java
│
├── your-message/
│     ├── your-message-core/               # Kafka events
│     │    └── com/yourcompany/your/service/message/
│     │         ├── listener/YourKafkaListener.java
│     │         └── publisher/YourKafkaPublisher.java
│     └── your-message-model/              # Avro models
│         └── src/main/resources/avro/
│             └── your.avsc
│
├── your-acceptance-test/                # Cucumber BDD tests
└── your-support/                        # Aggregation, Jacoco reports
```

---

## Estructura del Monorepo lg5-spring

El propio framework tiene su propia estructura monorepo:

```
lg5-spring/
│
├── lg5-spring-parent/           # BOM - Gestiona todas las versiones
├── lg5-spring-starter/          # spring-boot-starter mínimo
├── lg5-spring-api-rest/         # Spring Web + Validation
├── lg5-spring-data-jpa/         # JPA + PostgreSQL + Liquibase
├── lg5-spring-client/           # OpenFeign
├── lg5-spring-kafka/            # Kafka sub-system
│    ├── lg5-spring-kafka-config/
│    ├── lg5-spring-kafka-model/
│    ├── lg5-spring-kafka-producer/
│    └── lg5-spring-kafka-consumer/
├── lg5-spring-outbox/           # Outbox Pattern
├── lg5-jvm-saga/                # Saga Pattern
├── lg5-common/                  # DDD shared
│    └── lg5-common-domain/
│    └── lg5-common-application-service/
├── lg5-spring-logger/           # ELK Logging
├── lg5-jvm-utils/               # Lombok, Guava, MapStruct
├── lg5-spring-testcontainers/   # Test Containers
├── lg5-spring-integration-test/ # Integration Test base
├── lg5-spring-acceptance-test/  # Cucumber BDD
└── lg5-jvm-unit-test/           # JUnit 5 + Mockito
```

---

## Archivos Clave por Módulo

Cada módulo en el monorepo contiene:

| Archivo | Propósito |
|---------|-----------|
| build.gradle.kts | Declaración de dependencias usando version catalog |
| src/main/java/ | Código fuente Java/Kotlin |
| src/test/java/ | Tests unitarios |

### El Archivo de Versiones

El archivo central de versionado es `gradle/libs.versions.toml`:

```toml
[versions]
springboot-version = "3.5.14"
springframework-version = "6.2.2"
postgres-version = "42.7.5"
liquibase-version = "4.31.0"
avro-version = "1.12.0"
spring-kafka-version = "3.3.2"

[libraries]
springboot-starter = { group = "org.springframework.boot", name = "spring-boot-starter" }
springboot-start-web = { group = "org.springframework.boot", name = "spring-boot-starter-web" }
postgresql = { group = "org.postgresql", name = "postgresql" }
```

---

## Flujo de Construcción

```bash
# Construir todo el monorepo
make all-build

# Publicar en Maven Local
make publish-local
```
