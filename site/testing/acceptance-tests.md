# Acceptance Tests (ATDD)

## Framework: Cucumber 7 + Testcontainers + JUnit 5

Los **Acceptance Tests** validan el comportamiento del sistema con Tests de aceptación basados en Gherkin.

## Estructura de un AT

```gherkin
# blank-service.feature
Feature: Blank Service
  As a customer
  I want to create a blank
  So that data is persisted

  Background:
    Given a blank command

   Scenario: the blank should be CREATED
    When blank is created
    Then the blank will be created
     And the blank created event will be sent
```

## Ejecución

```bash
# Todos los ATs
make run-acceptance-test

# AT específico
make run-at-spec TEST_NAME=BlankAcceptanceT
```

## Reportes

Los resultados se publican en:
https://blank-service-atdd.web.app/atdd/
