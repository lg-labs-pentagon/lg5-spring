# lg5-spring-data-jpa

`lg5-spring-data-jpa` agrega soporte completo para persistencia: Spring Data JPA, PostgreSQL y Liquibase.

---

## Propósito

Agrega automáticamente:

- `spring-boot-starter-data-jpa`
- `postgresql` driver
- `liquibase-core` para migrations

---

## Dependencias

```kotlin
// lg5-spring-data-jpa/build.gradle.kts
dependencies {
    api(libs.springboot.data.jpa)
    api(libs.postgresql)
    api(libs.liquibase)
}
```

---

## Uso

```xml
<dependency>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-data-jpa</artifactId>
</dependency>
```
