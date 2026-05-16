# lg5-spring-api-rest

`lg5-spring-api-rest` es el módulo que configura Spring Web, Actuator y Validation para crear APIs REST.

## Propósito

Agrega automáticamente:

- `spring-boot-starter-web`
- `spring-boot-starter-actuator`
- `spring-boot-starter-validation`
- `GlobalExceptionHandler`

---

## Dependencias

```kotlin
// lg5-spring-api-rest/build.gradle.kts
dependencies {
    api(libs.springboot.start.web)
    api(libs.springboot.start.actuator)
    api(libs.springboot.validation)
    implementation(libs.slf4j.api)
}
```

---

## GlobalExceptionHandler

El módulo incluye un `@ControllerAdvice` que maneja errores automáticamente:

```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        log.error("Unexpected error", e);
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorDTO("Internal Server Error", "Unexpected error!"));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(ValidationException ve) {
        String message = ve.getMessage();
        log.warn("Validation failed: {}", message);
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorDTO("Validation Error", message));
    }
}
```

---

## ErrorDTO

```java
public record ErrorDTO(String code, String message) {}
```

---

## Uso en tu Servicio

```xml
<dependency>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-api-rest</artifactId>
</dependency>
```

```java
@RestController
@RequestMapping("/blank")
public class BlankController {
    @PostMapping
    public ResponseEntity<CreateBlankResponse> addBlank(@RequestBody CreateBlankCommand cmd) {
        return ResponseEntity.accepted()
            .body(service.createBlank(cmd));
    }
}
```

---

## Code por Default

| Código | Causa | Descripción |
|--------|-------|-------------|
| 200 OK | Normal | Operación exitosa |
| 202 Accepted | Async | Operación asíncrona (CREATE) |
| 400 Bad Request | Validation | ValidationException |
| 500 Internal | Exception | Unexpected error! |
