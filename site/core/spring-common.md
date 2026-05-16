# lg5-common

`lg5-common` agrupa las librerías compartidas de dominio y servicios de aplicación.

## Submódulos

| Submódulo | Grupo Maven | Propósito |
|---|---|---|
| `lg5-common-domain` | `lg5.common` | Primitivas de DDD (AggregateRoot, Entity) |
| `lg5-common-application-service` | `lg5.common` | Validación, spring-tx, JSON |

## lg5-common-domain

```kotlin
dependencies {
     api(libs.ddd-common-domain)     // com.labs.lg.pentagon:ddd-common-domain
     api(project(":lg5-jvm-utils")) {
         exclude(libs.lombok.get().group, libs.lombok.get().name)
         exclude(libs.mapstruct.get().group, libs.mapstruct.get().name)
     }
}
```

## lg5-common-application-service

```kotlin
dependencies {
     api(libs.springboot-validation)     // javax validation
     api(libs.spring-tx)                // spring-tx (TransactionTemplate)
     api(libs.springboot-json)          // spring-boot-starter-json
     api(project(":lg5-jvm-utils"))
}
```

## Uso

```xml
<dependency>
     <groupId>lg5.common</groupId>
     <artifactId>lg5-common-domain</artifactId>
</dependency>
<dependency>
     <groupId>lg5.common</groupId>
     <artifactId>lg5-common-application-service</artifactId>
</dependency>
```
