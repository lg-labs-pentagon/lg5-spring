# Paso 7: Testing

## 7.1: Pyramid de Testing

blank-service implementa un **Testing Pyramid** completo:

```
        / \
       / AT \        Cucumber BDD + Testcontainers
      /______\       (Acceptance Tests)     1 test
     /        \      ────────────────────────
    /__________\     Integration Tests       4-8 tests
   /             \     ───────────────────────
  /_______________\    Unit Tests            10-50 tests
 /      /    \    \    ───────────────────────
/______/______\____\
```

## 7.2: Unit Tests

```java
// BlankTest.java - Test del Aggregate Root
package com.blanksystem.blank.service.domain;

import com.blanksystem.blank.service.domain.entity.Blank;
import com.blanksystem.blank.service.domain.exception.BlankDomainException;
import com.blanksystem.blank.service.domain.valueobject.BlankId;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlankTest {

     @Test
     void it_should_create_a_blank_successfully() {
         Blank blank = new Blank(new BlankId(UUID.randomUUID()));
         assertDoesNotThrow(blank::validate);
      }

     @Test
     void it_should_throw_exception_when_blank_id_is_null() {
         Blank blank = new Blank(new BlankId(null));
         assertThrows(BlankDomainException.class, blank::validate);
      }
}
```

Makefile para correr un unit test específico:

```bash
make run-ut-spec TEST_NAME=BlankTest
```

## 7.3: Integration Tests

```java
// BlankCreatorIT.java
package com.blanksystem.blank.service.container.api;

// ... imports ...

class BlankCreatorIT extends Bootstrap {

     @Autowired ReportRepository reportRepository;
     @Autowired TestKafkaListener testKafkaListener;
     @Autowired ThirdSystemClient client;

     @Test
     void it_should_create_a_blank_using_api() {
         // given
         var command = new CreateBlankCommand(UUID.randomUUID());

         // when
         Response response = given(requestSpecification)
             .body(command)
             .when()
             .post("/blank");

         // then
         response.then().statusCode(HttpStatus.ACCEPTED.value());

         await().atMost(20, TimeUnit.SECONDS)
             .untilAsserted(this::thenReceivedMessageSuccessfully);
      }
}
```

Makefile para correr integración específica:

```bash
make run-it-spec TEST_NAME=BlankCreatorIT
```

## 7.4: Acceptance Tests (ATDD)

### Feature File (Gherkin)

```gherkin
# blank-service.feature
Feature: Blank Repository Template
  As a customer
  I want to create a blank using the repository template
  So that data is persisted

  Background:
    Given a blank command

  @case1
  Scenario: the blank should be CREATED when use the repository template
    When blank is created
    Then the blank will be created using the repository template
     And the blank created event will be sent
     AND the third system will be called to report the blank created

  @case2
  Scenario: the blank should be REPORTED when receive a blank created
    Given a blank stored
    When the blank created event is sent
    Then the blank created event will be sent
     AND the third system will be called to report the blank created
```

### Testcontainer Setup

```java
// TestContainersLoader.java (Cucumber)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class TestContainersLoader {

     @LocalContainer
     static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17.0")
         .withUsername("sa")
         .withPassword("sa")
         .withDatabaseName("postgres")
         .withNetwork(Network.newNetwork())
         .withNetworkAliases("postgres");

     @LocalContainer
     static KafkaContainer kafka = new KafkaContainer("confluentinc/cp-kafka:7.8.1")
         .withId(1)
         .withEnv("KAFKA_LISTENERS", "...")
         .withEnv("KAFKA_ADVERTISED_LISTENERS", "...");

     static {
         postgres.start();
         kafka.start();
     }
}
```

Makefile para correr acceptance test:

```bash
make run-at-spec TEST_NAME=BlankAcceptanceT
```

## 7.5: Makefile Commands

| Command | Descripción |
|---|---|
| `make all-build` | Construye todo |
| `make run-ut-spec TEST_NAME=X` | Unit test específico |
| `make run-it-spec TEST_NAME=X` | Integration test específico |
| `make run-at-spec TEST_NAME=X` | Acceptance test específico |
| `make run-test-spec TEST_NAME=X` | Cualquier test (lento) |
| `make run-checkstyle` | Validación de código Google Style |

## 7.6: Test Quality Metrics

```
Sonar Results:
├── Checkstyle: 3/4 (Google Java Style validation)
├── Unit Test: 2/4 (JUnit 5)
├── Integration Test: 3/4 (Testcontainers + JUnit 5 + Rest-Assured)
└── Acceptance Test: 4/4 (Cucumber + Testcontainers + JUnit 5 + Rest-Assured)
```
