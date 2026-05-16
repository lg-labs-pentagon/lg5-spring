# Visión General de las Librerías

lg5-spring está compuesto por **~20 submódulos Gradle** organizados en capas. Cada módulo es una librería independiente que puede ser usada de forma individual o combinada con otras.

## Dependencias entre Módulos

```
lg5-jvm-utils (base: Lombok, Guava, MapStruct)
     │
     ├── lg5-common-domain
     └── lg5-common-application-service
          │
          ├── lg5-spring-starter ──────────────────┐
          │                                         │
          ├── lg5-spring-api-rest ────────────┐     │
          │                                     │     │
          ├── lg5-spring-data-jpa ────────────┼─┐   │
          │                                     │ │   │
          ├── lg5-spring-client ────────────────┼─┼───┼── Main services
          │                                     │ │   │   │
          ├── lg5-spring-kafka-config ──────────┼─┘   │   │
          ├── lg5-spring-kafka-model ───────────┘     │   │
          ├── lg5-spring-kafka-producer ────────┐     │   │
          ├── lg5-spring-kafka-consumer ────────┼─┐   │   │
          ├── lg5-spring-outbox ────────────────┼─┼───┼── Event reliability
          ├── lg5-jvm-saga ─────────────────────┼─┘   │   │
          ├── lg5-spring-logger ──────────────────┘     │   │
          │                                         │     │   │
          ├── lg5-spring-testcontainers ──────────┘     │   │
          ├── lg5-spring-integration-test ────────┐     │   │
          │                                       │     │   │
          ├── lg5-spring-acceptance-test ─────────┼─┐   │   │
          │                                        │ │   │   │
          └── lg5-jvm-unit-test ───────────────────┘ │   │   │
                                                     │ │   │   │
lg5-spring-parent (BOM) ── manages all the above ───└─┴───┴── Main
```

## Lista de Módulos

| Módulo | Grupo Maven | Descripción |
|--------|-------------|-------------|
| **lg5-spring-parent** | `com.lg5.spring` | BOM - Gestiona todas las versiones |
| **lg5-spring-starter** | `com.lg5.spring` | spring-boot-starter mínimo |
| **lg5-spring-api-rest** | `com.lg5.spring` | Spring Web + Validation + Actuator |
| **lg5-spring-data-jpa** | `com.lg5.spring` | Spring Data JPA + PostgreSQL + Liquibase |
| **lg5-spring-client** | `com.lg5.spring` | OpenFeign REST Client |
| **lg5-spring-kafka-config** | `lg5.common.kafka` | @ConfigurationProperties de Kafka |
| **lg5-spring-kafka-model** | `lg5.common.kafka` | Apache Avro schemas |
| **lg5-spring-kafka-producer** | `lg5.common.kafka` | Kafka Producer con Avro |
| **lg5-spring-kafka-consumer** | `lg5.common.kafka` | Kafka Consumer con Avro |
| **lg5-spring-outbox** | `com.lg5.spring.outbox` | Outbox pattern (OutboxStatus, OutboxScheduler) |
| **lg5-jvm-saga** | `com.lg5.jvm` | Saga Step interface (process/rollback) |
| **lg5-common-domain** | `lg5.common` | DDD primitives (AggregateRoot, Entity) |
| **lg5-common-application-service** | `lg5.common` | Validación + spring-tx + JSON |
| **lg5-spring-logger** | `com.lg5.spring` | ELK Logging (Logstash, Janino, Sleuth) |
| **lg5-jvm-utils** | `com.lg5.jvm` | Lombok + Guava + Commons Lang + MapStruct |
| **lg5-spring-utils** | `com.lg5.spring` | DevTools + Docker Compose + Logging |
| **lg5-spring-testcontainers** | `com.lg5.spring` | PostgreSQL, Kafka, WireMock containers |
| **lg5-spring-integration-test** | `com.lg5.spring` | Lg5TestBoot base classes |
| **lg5-spring-acceptance-test** | `com.lg5.spring` | Cucumber BDD |
| **lg5-jvm-unit-test** | `com.lg5.jvm` | JUnit Jupiter + Mockito |
