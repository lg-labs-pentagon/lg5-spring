# lg5-spring-starter

`lg5-spring-starter` es el módulo más minimalista del framework. Provee únicamente el spring-boot-starter base.

## Propósito

Es el **punto de partida** para servicios que necesitan control total sobre qué starters de Spring Boot usan.

---

## Dependencias

```kotlin
// lg5-spring-starter/build.gradle.kts
dependencies {
    api(libs.springboot.starter)
}
```

---

## Cuándo Usarlo

- Servicios con necesidades muy específicas
- Cuando necesitas un blank slate para configurar Spring Boot a tu medida
- Para crear tu propio starter personalizado encima

---

## Ejemplo de Uso

```xml
<dependency>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-starter</artifactId>
</dependency>
```

Luego agrega manualmente los starters que necesites:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
