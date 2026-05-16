# GlobalExceptionHandler API

## Clase

```java
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // Manejo global de excepciones
}
```

## Métodos

### `handleException(Exception e)`

 Maneja excepciones no mapeadas (default 500).

```java
@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorDTO> handleException(Exception e) {
    log.error("Unexpected error", e);
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorDTO("Internal Server Error", "Unexpected error!"));
}
```

**Código de respuesta:** 500 (INTERNAL_SERVER_ERROR)

### `handleValidationException(ValidationException ve)`

Maneja validaciones fallidas (default 400).

```java
@ExceptionHandler(ValidationException.class)
public ResponseEntity<ErrorDTO> handleValidationException(VE ve) {
    String message = ve.getMessage();
    if (ve instanceof ConstraintViolationException) {
        message = ((CVE) ve).getConstraintViolations()
            .stream().map(CV::getMessage)
            .collect(Collectors.joining("--"));
    }
    log.warn("Validation failed: {}", message);
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(new ErrorDTO("Validation Error", message));
}
```

**Código de respuesta:** 400 (BAD_REQUEST)

---

Ver también: [lg5-spring-api-rest documentation](/core/spring-api-rest)
