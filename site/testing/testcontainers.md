# Testcontainers

## Framework: Testcontainers 1.20.4

## Contenedores Soportados

| Contenedor | Imagen | Puerto | Propósito |
|---|---|---|---|
| PostgreSQL | `postgres:17.2` | 5432 | Base de datos |
| Kafka | `confluentinc/cp-kafka:7.8.1` | 9092/9093 | Mensajería |
| Schema Registry | `cp-schema-registry:7.8.1` | 8081 | Avro |
| Wiremock | `wiremock/wiremock:3.11.0` | 8080 | Mock HTTP |

## Estructura de ContainerConfigs

```
ContainerConfig (interface)
     └── MultiContainerConfig extends ContainerConfig
          └── KafkaContainerCustomConfig (abstract)
          └── ConfluentKafkaContainerCustomConfig (class)
       └── WiremockContainerCustomConfig (abstract)
```

## Uso

```java
@TestConfiguration
public class PostgresContainerCustomConfig extends BaseContainerCustomConfig
    implements ContainerConfig {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>("postgres:17.2")
            .withNetwork(Network.newNetwork())
            .withNetworkAliases("postgres")
            .withDatabaseName("postgres")
            .withUsername("sa")
            .withPassword("sa");
     }
}
```
