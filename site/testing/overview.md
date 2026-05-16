# Testing Overview

## Pyramid de Testing en lg5-spring

lg5-spring implementa un **Testing Pyramid** completo con tres niveles de prueba:

```
         / \ 
        / AT \        1 test - Cucumber BDD
       /______\        Acceptance Tests
      /         \     ───────────────────────
     / ___________ \   4-8 tests - Integration Tests
    / /            \ \  ───────────────────────
   / /______________\ \  10-50 tests - Unit Tests
  /____________________\
```

## Niveles de Testing

| Nivel | Framework | Contenedores | Enfoque |
|-------|-----------|--------------|---------|
| **Unit Tests** | JUnit 5 + Mockito | Ninguno | Lógica pura del dominio |
| **Integration Tests** | JUnit 5 + Testcontainers | Postgres, Kafka | Lógica + Infraestructura |
| **Acceptance Tests** | Cucumber 7 + Testcontainers | Postgres, Kafka | Comportamiento E2E |

## Cuándo Usar Cada Nivel

### Unit Tests

```
┌──────────────────────────────────────────────────────────┐
│  Cuándo usar Unit Tests                                   │
│                                                           │
│  ● Testing de AggregateRoots                             │
│  ● Testing de ValueObjects                               │
│  ● Testing de lógica de dominio pura                       │
│  ● Testing de mappers (MapStruct)                        │
│                                                           │
│  Ejemplo:                                               │
│    - BlankTest.java                                      │
│    - BlankDomainServiceTest.java                         │
│    - BlankDataAccessMapperTest.java                      │
└──────────────────────────────────────────────────────────┘
```

### Integration Tests

```
┌──────────────────────────────────────────────────────────┐
│  Cuándo usar Integration Tests                           │
│                                                           │
│  ● Testing de Controllers REST                          │
│  ● Testing de Repositorios con TestContainers            │
│  ● Testing de Kafka Consumers                            │
│  ● Testing end-to-end con base de datos real           │
│                                                           │
│  Ejemplo:                                               │
│    - BlankCreatorIT.java                                 │
│    - BlankRepositoryIT.java                              │
│    - ReportAdapterIT.java                                │
└──────────────────────────────────────────────────────────┘
```

### Acceptance Tests

```
┌──────────────────────────────────────────────────────────┐
│  Cuándo usar Acceptance Tests (BDD)                        │
│                                                           │
│  ● Testing comportamiento de negocio en inglés        │
│  ● Testing E2E completo (HTTP + DB + Kafka)             │
│  ● Testing con third-party systems                        │
│  ● Validación de Reglas de Negocio                        │
│                                                           │
│  Ejemplo:                                               │
│    - blank-service.feature                               │
│    - BlankAcceptanceTest.java                            │
│    - StepDefinitions.java                                │
└──────────────────────────────────────────────────────────┘
```

## Frameworks Utilizados

| Framework | Propósito | Versión |
|-----------|-----------|---------|
| JUnit Jupiter | Unit & Integration tests | 5.12.0-RC1 |
| Mockito | Mocking de objetos | 5.15.2 |
| Cucumber | BDD / Gherkin | 7.21.1 |
| Testcontainers | Postgres, Kafka, WireMock | 1.20.4 |
| Rest-Assured | Testing de endpoints REST | 5.5.0 |
| Awaitility | Esperas async para eventos | 4.2.2 |
| Allure | Reportes visuales de tests | 7.2.0 |

## Makefile Commands

| Comando | Descripción |
|---------|-------------|
| `make all-build` | Construye todo |
| `make run-ut-spec TEST_NAME=X` | Unit test por nombre |
| `make run-it-spec TEST_NAME=X` | Integration test por nombre |
| `make run-at-spec TEST_NAME=X` | Acceptance test por nombre |
| `make run-test-spec TEST_NAME=X` | Cualquier test (lento) |
| `make run-checkstyle` | Validación código |
