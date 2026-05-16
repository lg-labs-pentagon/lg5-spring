# Distribución de Módulos

## Visión General del Monorepo

```
lg5-spring/                                        # Root del monorepo
│
├── lg5-spring-parent/                            # BOM Central
│    └── build.gradle.kts                          # DependencyManagement + PluginManagement
│
├── lg5-spring-starter/                           # Starter mínimo
│    └── spring-boot-starter                     
│
├── lg5-spring-api-rest/                          # REST API support
│     
├── lg5-spring-data-jpa/                             # JPA, PostgreSQL, Liquibase
│
├── lg5-spring-client/                            # OpenFeign
│
├── lg5-spring-kafka/                             # Kafka sub-system
│    ├── lg5-spring-kafka-config/              # @ConfigurationProperties
│    ├── lg5-spring-kafka-producer/            # KafkaProducer
│    ├── lg5-spring-kafka-consumer/            # KafkaConsumer
│    └── lg5-spring-kafka-model/                  # Apache Avro
│
├── lg5-spring-outbox/                             # Outbox pattern
│
├── lg5-jvm-saga/                                   # Saga pattern
│
├── lg5-common/                                      # Shared DDD
│    ├── lg5-common-domain/                        # DDD primitives
│    └── lg5-common-application-service/         # App service deps
│
├── lg5-spring-logger/                              # ELK Logging
│
├── lg5-jvm-utils/                                    # Lombok, Guava, MapStruct
│
├── lg5-spring-testcontainers/                      # Test containers
│
├── lg5-spring-integration-test/                    # Test base classes
│
├── lg5-spring-acceptance-test/                    # Cucumber BDD
│
└── lg5-jvm-unit-test/                               # JUnit 5 + Mockito
```
