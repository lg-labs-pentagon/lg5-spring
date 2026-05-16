# Introducción

## ¿Qué es lg5-spring?

**lg5-spring** es un **Framework BOM (Bill of Materials)** de Lg Pentagon (LgLabs) diseñado para crear microservicios Spring Boot de forma rápida y estandarizada.

Piensa en lg5-spring como un **starter kit completo** para microservicios: en lugar de configurar manualmente dependencias, plugins, logging, testing y patrones de arquitectura para cada nuevo servicio, lg5-spring provee un **Parent POM preconfigurado** y un conjunto de **módulos reusable** que cubren todos los casos de uso comunes.

## Filosofía del Proyecto

La filosofía detrás de lg5-spring se resume en su README:

> _"Manage all Spring Boot dependencies to create new applications using this library. It's just faster."_

### Principios Clave

1. **Hexagonal Architecture (Ports-and-Adapters)** — La lógica de dominio está aislada de los frameworks externos
2. **Domain-Driven Design (DDD)** — Modelos de dominio ricos con Entitys, AggregateRoots y ValueObjects
3. **Event-Driven Architecture** — Kafka + Avro para comunicación asíncrona entre servicios
4. **Outbox Pattern** — Garantía de eventos confiables con patrones de transacción
5. **SAGA Pattern** — Transacciones distribuidas compensables
6. **Testing Pyramid** — Unit Tests → Integration Tests → Acceptance Tests (Cucumber BDD)

## Stack Tecnológico

| Componente | Versión |
|---|---|
| Spring Boot | 3.5.14 |
| Spring Framework | 6.2.2 |
| JDK | 21 |
| Kotlin | 2.2.0 |
| PostgreSQL | 42.7.5 |
| Kafka | spring-kafka 3.3.2 + Avro 1.12.0 |
| Testcontainers | 1.20.4 |
| Cucumber | 7.21.1 |
| MapStruct | 1.6.3 |

## ¿Por qué usar lg5-spring?

| Motivación | sin lg5-spring | con lg5-spring |
|---|---|---|
| Crear un nuevo microservicio | Días configurando dependencias | Horas, solo te enfocas en el dominio |
| Testing | Configurar Testcontainers manualmente | `lg5-spring-testcontainers` listo para usar |
| Mensajería | Implementar Kafka desde cero | `lg5-spring-kafka` con producer/consumer/outbox |
| Logging | Configurar ELK manualmente | `lg5-spring-logger` con MDC y Sleuth tracing |
| Calidad de código | Sin Checkstyle | Google Java Style preconfigurado |
| Deploy | Configurar JIB/Docker desde cero | Plugin JIB en el parent POM |

## Ejemplo de Uso

Para usar lg5-spring en tu proyecto:

```xml
<!-- pom.xml -->
<parent>
    <groupId>com.lg5.spring</groupId>
    <artifactId>lg5-spring-parent</artifactId>
    <version>1.0.0-alpha.6ff1b95</version>
</parent>
```

Luego, agregar solo los módulos que necesitas:

```xml
<dependencies>
    <dependency><groupId>com.lg5.spring</groupId><artifactId>lg5-spring-api-rest</artifactId></dependency>
    <dependency><groupId>com.lg5.spring</groupId><artifactId>lg5-spring-data-jpa</artifactId></dependency>
    <dependency><groupId>com.lg5.spring</groupId><artifactId>lg5-spring-kafka</artifactId></dependency>
</dependencies>
```

## Repositorios

- **Repositorio Principal:** [lg-labs-pentagon/lg5-spring](https://github.com/lg-labs-pentagon/lg5-spring)
- **Ejemplo (blank-service):** [lg-labs/blank-service](https://github.com/lg-labs/blank-service)
- **Infraestructura:** [lg-labs/blank-infra](https://github.com/lg-labs/blank-infra)
