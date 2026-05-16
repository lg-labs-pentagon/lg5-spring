# ErrorDTO API

## Record

```java
// com/lg5/spring/api/rest/ErrorDTO.java
public record ErrorDTO(String code, String message) {}
```

### Campos

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `code` | String | Código de error ("Validation Error" / "Internal Server Error") |
| `message` | String | Mensaje detallado del error |

### Respuestas

#### 400 Bad Request

```json
{
    "code": "Validation Error",
    "message": "must not be null"
}
```

#### 500 Internal Server Error

```json
{
    "code": "Internal Server Error", 
    "message": "Unexpected error!"
}
```

## Uso en Controllers

```java
@RestController
@RequestMapping("/blank")
public class BlankController {
    @PostMapping
    public ResponseEntity<CreateBlankResponse> createBlank(
            @RequestBody CreateBlankCommand cmd) {
        return ResponseEntity.accepted()
            .body(service.createBlank(cmd));
    }
}
```

El `GlobalExceptionHandler` maneja los errores automáticamente para ti.

---

Ver también: [GlobalExceptionHandler API](/api/global-exception-handler) | [lg5-spring-api-rest](/core/spring-api-rest)
