# lg5-spring — Convenciones de Código

## Overview

Este documento define las convenciones de código, estilo y buenas prácticas del proyecto `lg5-spring`.

---

## Lenguajes y Versiones

| Lenguaje | Versión | Notas |
|---|---|---|
| **Java** | 21 | JDK 21 (Amazon Corretto recomendada) |
| **Kotlin** | 2.2.0 | Para data classes, interfaces, configs |
| **Gradle** | 8.x (Kotlin DSL) | Para build del monorepo |
| **Maven** | 3.8+ | Para construir microservicios |

---

## Nomenclatura

### Nombres de Clases

| Tipo | Convención | Ejemplo |
|---|---|---|
| **Entity** | Nombre del contexto (singular) | `Blank`, `BlankRoot` |
| **ValueObject** | Nombre + sufijo | `BlankId`, `BlankName`, `BlankType` |
| **AggregateRoot** | Nombre del contexto | `Blank`, `Order` |
| **DomainEvent** | `<Entity><Event>` | `BlankCreatedEvent`, `OrderShippedEvent` |
| **ApplicationService** | `<Entity>ApplicationService` | `BlankApplicationService` |
| **Repository** | `<Entity>Repository` | `BlankRepository`, `OrderRepository` |
| **Controller** | `<Entity>Controller` | `BlankController`, `OrderController` |
| **DTO** | `<Entity><Action>Command` / `<Entity><Action>Response` | `CreateBlankCommand`, `UpdateBlankResponse` |
| **Mapper** | `<Entity><Context>Mapper` | `BlankDataAccessMapper`, `BlankMessagingMapper` |
| **Adapter** | `<Entity>Adapter`, `<Entity>RepositoryImpl` | `BlankAdapter`, `BlankRepositoryImpl` |
| **Exception** | `<Entity>Exception`, `<Entity>RuntimeException` | `BlankDomainException`, `BlankApplicationServiceException` |
| **ConfigData** | `*ConfigData` | `KafkaConfigData`, `KafkaProducerConfigData` |
| **Listener** | `<Entity>KafkaListener`, `<Entity>Listener` | `BlankKafkaListener` |

### Nombres de Métodos

| Tipo | Convención | Ejemplo |
|---|---|---|
| **Create Action** | `create<Entity>` | `createBlank()` |
| **Read Action** | `find<Entity>By*`, `get<Entity>By*` | `findById()`, `findByName()` |
| **Update Action** | `update*`, `modify*` | `update()`, `modify()` |
| **Delete Action** | `delete<Entity>`, `<entity>Delete` | `deleteBlank()` |
| **Saga Forward Action** | `process` | `process()` |
| **Saga Compensation Action** | `rollback` | `rollback()` |
| **Validation Action** | `validate` | `validate()` |
| **Mapping Action** | `to<Entity>`, `<entity>To<Entity>` | `toEntity()`, `blankToEntity()` |

### Nombres de Variables

| Tipo | Convención | Ejemplo |
|---|---|---|
| **Identifiers** | snake_case para UUIDs, camelCase para strings | `blankId`, `orderNumber` |
| **Constants** | UPPER_SNAKE_CASE | `NUM_PARTITIONS`, `DEFAULT_TIMEOUT` |
| **Booleans** | `is*`, `has*`, `can*` | `isValid`, `hasPermission` |

---

## Estructura de Paquete

### Estructura de Paquete:

| Paquete | Ubicación | Propósito |
|---|---|---|
| **your-api/** | Controllers + REST + DTOs | `com/your/your/api/rest/` |
| **your-container/** | Bootstrapping | `com/your/your/container/` + `Application.java` |
| **your-domain-core/** | Pure domain | `com/your/your/service/domain/` → `entity`, `valueobject`, `event`, `exception` |
| **your-application-service/** | Use cases + Sagas | `com/your/your/service/domain/` |
| **your-data-access/** | DB Infrastructure | `com/your/your/service/data/` |
| **your-message-core/** | Kafka events | `com/your/your/service/message/` |
| **your-message-model/** | Avro models | `src/main/resources/avro/` |

### Paquete:

| Paquete | Ubicación | Propósito |
|---|---|---|
| **your-api/** | Controllers + REST + DTOs | `com/your/your/api/rest/` |
| **your-container/** | Bootstrapping | `com/your/your/container/` + `Application.java` |
| **your-domain-core/** | Pure domain | `com/your/your/service/domain/` → `entity`, `valueobject`, `event`, `exception` |
| **your-application-service/** | Use cases + Sagas | `com/your/your/service/domain/` |
| **your-data-access/** | DB Infrastructure | `com/your/your/service/data/` |
| **your-message-core/** | Kafka events | `com/your/your/service/message/` |
| **your-message-model/** | Avro models | `src/main/resources/avro/` |

| **your-api/** | Controllers + REST + DTOs | `com/your/your/api/rest/` |
| **your-container/** | Bootstrapping | `com/your/your/container/` + `Application.java` |
| **your-domain-core/** | Pure domain | `com/your/your/service/domain/` → `entity`, `valueobject`, `event`, `exception` |
| **your-application-service/** | Use cases + Sagas | `com/your/your/service/domain/` |
| **your-data-access/** | DB Infrastructure | `com/your/your/service/data/` |
| **your-message-core/** | Kafka events | `com/your/your/service/message/` |
| **your-message-model/** | Avro models | `src/main/resources/avro/` |

| **your-api/** | Controllers + REST + DTOs | `com/your/your/api/rest/` |
| **your-container** | Bootstrapping | `com/your/your/container/` + `Application.java` |
| **your** | Pure domain | `com/your/your/service/domain/` → `entity`, `valueobject`, `event`, `exception` |
| **your-service** | Service | `com/your/your/service/domain/` |
| **your-data** | Service | `com/your/your/data/` → `entity`, `valueobject`, `event`, `exception` |
| **your-message-core** | Kafka events | `com/your/your/service/message/` |
| **your-message-model** | Avro models | `src/main/resources/avro/` |

---

## Arquitectura Hexagonal

### Estructura de Paquete

```
your-service/
├── your-api/                              # Controllers + REST + DTOs
│       └── com/your/your/api/rest/
│            ├── YourController.java         # Controller (REST endpoints)
│            └── dto/                        # API DTOs
│
├── your-container/                          # Application bootstrapping
│       └── com/your/your/container/
│            └── Application.java            # @SpringBootApplication
│
├── your-domain/
│      ├── your-domain-core/                 # Pure domain
│      │      └── com/your/your/service/domain/
│      │            ├── entity/               # AggregateRoots, entities
│      │            ├── valueobject/          # ValueObjects
│      │            ├── event/                # DomainEvents
│      │            ├── exception/            # DomainExceptions
│      │            ├── service/              # DomainService logic
│      │            └── mapper/               # Domain mappers
│      │
│      └── your-application-service/       # Application layer
│            └── com/your/your/service/domain/
│                 ├── dto/                   # Commands, Responses
│                 ├── ports/
│                 │     ├── input/
│                 │     │     ├── service/      # ApplicationService interface (I)
│                 │     │     └── message/      # MessageListener interfaces (I)
│                 │     └── output/
│                 │            ├── repository/   # Repository interfaces (I)
│                 │            └── message/      # EventPublisher interfaces (I)
│                 ├── yourservice/              # ApplicationServiceImpl + CommandHandlers
│                 └── mapper/                   # Domain mappers
│
├── your-data-access/                         # Infrastructure (DB)
│       └── com/your/your/service/data/
│            ├── adapter/                     # Port implementations (Repositories)
│            ├── entity/                      # JPA entities
│            ├── mapper/                      # Data mappers (MapStruct)
│            ├── repository/                  # Spring Data JPA interfaces
│            └── exception/                   # Data layer exceptions
│
├── your-message/
│      ├── your-message-core/                   # Kafka listeners, publishers
│      │      └── com/your/your/service/message/
│      │            ├── listener/                # KafkaListener implementations
│      │            ├── publisher/               # EventPublisher implementations
│      │            └── mapper/                  # Message mappers
│      │
│      └── your-message-model/                  # Avro model codegen
│            └── your/src/main/resources/avro/
│                 └── your.avsc                 # Avro schemas
│
├── your-external/                              # External adapters
│       └── com/your/your/service/external/
│            ├── client/                        # Feign clients
│            └── adapter/                         # 3rd party adapters
│
├── your-acceptance-test/                     # Cucumber BDD
│       └── com/your/your/acceptance/
│            ├── features/                    # .feature files
│            ├── stepdefinitions/             # Step definitions
│            └── world/                       # Gherkin world classes
│
└── your-support/                             # Aggregation, reports
```

---

## Estilo de Código

### Imports

- Usa imports cualificados (no `*`)
- Agrupa: `javax/java → imports → springframework → springboot → springdata → springcloud → kafka → springkafka → springbootstarter → springbootdata → springbootvalidation → springboottest → springbootactuator → springcloudstarter → springcloudstream → springcloudgateway → springcloudconfig → springclouddiscovery → springcloudsecurity → springcloudmonitoring → springcloudevents → springcloudstream → springcloudgateway → springcloudconfig → springclouddiscovery → springcloudsecurity → springcloudmonitoring → springcloudevents → springbootstarter → springbootdata → springbootvalidation → springboottest → springbootactuator → springcloudstarter → springcloudstream → springcloudgateway → springcloudconfig → springclouddiscovery → springcloudsecurity → springcloudmonitoring → springcloudevents`
- **Blank lines entre grupos**

```java
// GOOD
import java.util.Optional;
import java.util.UUID;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;
import com.yourcompany.your.service.domain.entity.Blank;

// BAD
import java.util.*;
import jakarta.*;
```

### Annotations

- Usa **Lombok** (`@Data`, `@Builder`, `@Value`, `@Slf4j`, `@RequiredArgsConstructor`) siempre que sea posible
- Usa **MapStruct** (`@Mapper`, `@Mapping`) para mapeo de objetos
- Usa **Jakarta Validation** (`@Valid`, `@NotNull`, `@Size`) para validación

### Logging

- Usa **SLF4J** con **Lombok** (`@Slf4j`)
- Usa **MDC** para contexto distribuido (traceId, spanId)
- Usa **JSON** logging para Logstash/Elasticsearch

### Excepciones

- Usa **Unchecked exceptions** (`RuntimeException`) para errores de aplicación
- Usa **Checked exceptions** solo para errores de infraestructura
- Usa **DomainExceptions** para errores de dominio (negocio)

```java
// Domain Exception
public class BlankDomainException extends RuntimeException {
    public BlankDomainException(String message) {
        super(message);
    }
}

// Application Exception
public class BlankApplicationServiceException extends RuntimeException {
    public BlankApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

---

## Testing

### Unit Tests

- **JUnit 5** (Jupiter) + **Mockito**
- **Nombres de tests** en snake_case: `it_should_...()` o `shouldBeValid_when_...()`
- **Arrange-Act-Assert** (AAA) pattern
- **Mockito para mocks**: `when(mock.method()).thenReturn(value)`

### Integration Tests

- **JUnit 5** + **Testcontainers** + **Rest-Assured**
- **Nombres de tests** en snake_case: `should_...()` o `shouldBeValid_when_...()`
- **Testcontainers** para PostgreSQL, Kafka, WireMock
- **Rest-Assured** para HTTP requests/responses

### Acceptance Tests

- **Cucumber 7** (Gherkin) + **Testcontainers**
- **Feature files** en inglés simple
- **Step definitions** en inglés simple
- **Gherkin** para testing de aceptación

### Nombres de Tests

```java
// GOOD
@Test
void it_should_create_a_blank_successfully() {
    // given
    ...
    // when
    ...
    // then
    ...
}

// GOOD
@Test
void it_should_throw_exception_when_blank_id_is_null() {
    // given...
    // when...
    // then...
}
```

---

## API REST

### Endpoints

- Usa **HTTP verbs** apropiadas: `GET`, `POST`, `PUT`, `PATCH`, `DELETE`
- Usa **RESTful URLs**: `/api/entity`, `/api/entity/{id}`
- Usa **JSON** para payloads (`application/json` o `application/vnd.api.v1+json`)

### Responses

| Código | Significado | Ejemplo |
|---|---|---|
| `200 OK` | Success (GET, PUT, PATCH) | `{"id": "uuid", "name": "value"}` |
| `201** | Created (POST) | `{"id": "uuid", "createdAt": "..."}` |
| `204 No Content` | Deleted | `null` |
| `400 Bad Request` | Validation error | `{"errors": [...]}` |
| `401 Unauthorized` | Auth error | `{"error": "unauthorized"}` |
| `404 Not Found` | Entity not found | `{"error": "not found"}` |
| `409 Conflict` | Conflict | `{"error": "conflict"}` |
| `500 Internal Error` | Server error | `{"error": "internal error"}` |

---

## Base de Datos

### PostgreSQL

- Usa **UUIDs** para primary keys (no auto-generated)
- Usa **timestamps** (`created_at`, `updated_at`)
- Usa **Liquibase** para migrations

### Liquibase

- Usa **YAML** para changelogs (no SQL files)
- Usa **versionado numérico**: `V1__initial.sql`
- Usa **author names**: `author: yourname`

### Naming

- **Tablas**: plural, lower_snake_case: `blanks`, `orders`
- **Columnas**: snake_case: `blank_id`, `created_at`
- **Índices**: `idx_<table>_<column>`
- **Constraints**: `fk_<table>_<column>`, `uq_table_column`

---

## Git Workflow

### Branches

| Branch | Propósito |
|---|---|
| `main` | Código production-ready |
| `feature/*` | Nuevas features |
| `fix/*` | Bug fixes |
| `release/*` | Releases |
| `hotfix/*` | Hotfixes urgentes |

### Commit Messages

Usa **Conventional Commits** y la ID de la tarea:

| Tipo | Propósito |
|---|---|
| `feat` | Nueva feature |
| `fix` | Bug fix |
| `docs` | Documentación |
| `style` | Style changes (formatting, naming) |
| `refactor` | Refactor sin cambios de comportamiento |
| `test` | Add/fix tests |
| `chore` | Build, deps, CI changes |

### Ejemplo de Commit Messages

```bash
# Feature + ID de tarea
feat(core): add outbox pattern support [LG-87]

# Fix
fix(kafka): fix consumer offset reset issue [LG-87]

# Documentation
docs: add architecture documentation [LG-87]

# Refactor
refactor(ddd): rename Blank to Order [LG-87]

# Test
test(unit): add unit tests for repository [LG-87]

# Chore
chore(deps): upgrade spring-boot to 3.5.14 [LG-87]
```

---

# Code Quality

## Checkstyle

- Usa **Google Java Style** (Google Java Style)
- **Longitud máxima de línea**: `120` caracteres

### Maven Plugins

| Plugin | Propósito |
|---|---|
| `maven-checkstyle-plugin` | Code quality |
| `puppycrawl-tools` | Checkstyle rules |

### Commands

```bash
# Check style
mvn checkstyle:check

# Report
mvn checkstyle:checkstyle
```

---

## Jacoco

- **Cobertura mínima**: 80%
- **Exclude Avro**: `**/avro/**`
- **Exclude Kafka**: `**/kafka/**`

### Commands

```bash
# Coverage
mvn jacoco:report

# Integration coverage
mvn jacoco:report-integration
```

---

# Security

## Best Practices

- **Never commit secrets** (passwords, keys, tokens)
- **Use environment variables** for sensitive config
- **Use** `application.properties` for non-sensitive config
- **Use** `application-<env>.yml` for **env-specific** config
- **Use** `spring.profiles.active` para **profiles**

## Security Checklist

- [ ] **No hardcoded passwords**
- [ ] **No hardcoded UUIDs**
- [ ] **No hardcoded Kafka URLs**
- [ ] **No hardcoded Spring Boot URLs**

---

# Performance

## Checklist

- [ ] **Use connection pooling**
- [ ] **Use pagination** for large queries
- [ ] **Use indexes** for large tables
- [ ] **Use Kafka** for **async** processing
- [ ] **Use Caching** for **read-heavy** data

---

# Monitoring

## Health Checks

- **Spring Boot Actuator** for **health** endpoints
- **Metrics** for **Spring Boot Actuator**
- **Sleuth** for **distributed** tracing

## Monitoring

- **Metrics** for **Spring Boot Actuator**
- **Logs** for **ELK** (Logstash, Janino)
- **Traces** for **Sleuth** (Spring Cloud Sleuth)

---

# Deploy

## Docker

- **Use** `eclipse-temurin:21-jre` **for** **base** images
- **Use** **JIB** for **multi-arch** **images**
- **Use** **profiles** for **AMD** and **ARM64**

## K8s

- **Use** **Kubernetes** for **deployment**
- **Use** **Helm** for **charts**
- **Use** **ArgoCD** for **CD**

---

# Contributing

## Steps

1. **Fork** the repo
2. **Create** a **branch** (`feature/...`)
3. **Commit** using **Conventional Commits**
4. **Push** to **GitHub**
5. **Create** a **Pull Request**

## Checklist

- [ ] **Lint** passes (Checkstyle)
- [ ] **Tests** pass (Unit + Integration + Acceptance)
- [ ] **Build** passes (Maven/Gradle)
- [ ] **Documentation** is updated
- [ ] **PR** is **reviewed** by **at least** one **reviewer**
