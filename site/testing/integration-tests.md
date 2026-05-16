# Integration Tests

## Framework: JUnit 5 + Testcontainers + Rest-Assured

Los **Integration Tests** validan el comportamiento del sistema con infraestructura real (PostgreSQL, Kafka).

## Lg5TestBoot Base Classes

```java
// Lg5TestBoot.java
@ActiveProfiles({"test", "local"})
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DisplayNameGeneration(ReplaceUnderscores.class)
public abstract class Lg5TestBoot {
    protected int port;
    protected RequestSpecification requestSpecification;

    @BeforeEach
    void setup() {
        requestSpecification = new RequestSpecBuilder()
            .setPort(port)
            .addHeader("Content-Type", "application/json")
            .build();
     }
}
```

## Ejemplo: Integration Test REST

```java
// BlankCreatorIT.java
class BlankCreatorIT extends Lg5TestBoot {

    @Autowired
    ReportRepository reportRepository;

    @Test
    void should_create_blank_via_api() {
        // given
        CreateBlankCommand cmd = new CreateBlankCommand(UUID.randomUUID());

        // when
        Response response = given(requestSpecification)
            .body(cmd)
            .when()
            .post("/blank");

        // then
        response.then().statusCode(HttpStatus.ACCEPTED.value());
    }
}
```

## Ejecución

```bash
# Todos los IT
make run-integration-test

# IT específico
make run-it-spec TEST_NAME=BlankCreatorIT
```
