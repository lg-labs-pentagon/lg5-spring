# lg5-spring-client

`lg5-spring-client` agrega soporte para **OpenFeign** — clientes REST declarativos para comunicación entre microservicios.

## Propósito

Agrega automáticamente:

- `spring-cloud-starter-openfeign` 4.2.0
- `FeignClientConfiguration` con Basic Auth
- `CustomErrorDecoder` para mapear HTTP status a excepciones

---

## Dependencias

```kotlin
// lg5-spring-client/build.gradle.kts
dependencies {
    api(libs.openfeign)
}
```

---

## FeignClientConfiguration

```yaml
# application.yaml properties que necesitas definir
third:
    basic:
        auth:
            username: your-username
            password: your-password
```

```java
@Configuration
public class FeignClientConfiguration {
    @Value("${third.basic.auth.username}")
    private String username;

    @Value("${third.basic.auth.password}")
    private String password;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(username, password);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
```

---

## CustomErrorDecoder

Mapea códigos HTTP a excepciones Java:

| HTTP Status | Excepción |
|---|---|
| 400 | BadRequestException |
| 404 | NotFoundException |
| other | Exception default |

---

## Ejemplo de Uso

```xml
<dependency>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-client</artifactId>
</dependency>
```

```java
@FeignClient(name = "thirdSystem", url = "${third.system.url}")
public interface ThirdSystemClient {
    @GetMapping("/api/resource")
    ResponseModel getResource();
}
```
