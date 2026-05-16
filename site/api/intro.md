# API Reference: lg5-spring

## Introducción

Esta sección.documenta las interfaces y clases principales del framework lg5-spring desde la API perspectiva. Cada API se organiza por módulo.

## Módulos Principales

- [GlobalExceptionHandler](/api/global-exception-handler)
- [KafkaProducer](/api/kafka-producer)
- [KafkaConsumer](/api/kafka-consumer)
- [SagaStep](/api/saga-step)
- [OutboxScheduler](/api/outbox-scheduler)
- [ErrorDTO](/api/error-dto)

## Arquitectura del Proyecto

```
lg5-spring/
├── lg5-spring-parent/          <- BOM
├── lg5-spring-starter/         <- spring-boot-starter mínimo
├── lg5-spring-api-rest/        <- Spring Web + Validation
├── lg5-spring-data-jpa/        <- Spring Data JPA + PostgreSQL
├── lg5-spring-client/          <- OpenFeign
├── lg5-spring-kafka/           <- Kafka (config, producer, consumer, model)
├── lg5-spring-outbox/          <- Outbox Pattern
├── lg5-jvm-saga/               <- Saga Pattern
├── lg5-common/                 <- DDD shared
├── lg5-spring-logger/          <- ELK Logging
├── lg5-jvm-utils/              <- Lombok, Guava, MapStruct
├── lg5-spring-testcontainers/  <- Testcontainers
├── lg5-spring-integration-test/- <- Integration Test base
├── lg5-spring-acceptance-test/-<- Cucumber BDD
└── lg5-jvm-unit-test/          <- JUnit 5 + Mockito
```
