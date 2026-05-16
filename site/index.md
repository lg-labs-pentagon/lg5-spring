# <span class='prefix'>lg5</span><span class='accent'>spring</span>

<span class='tagline'>Spring Boot 3.5 + JDK 21 — Framework for Building Microservices Faster</span>

<div class='badge-group'>
   <span class='badge badge-spring'>Spring Boot 3.5</span>
   <span class='badge badge-java'>JDK 21</span>
   <span class='badge badge-kotlin'>Kotlin 2.2</span>
   <span class='badge badge-grape'>Gradle Kotlin</span>
</div>

<div class='feature-grid'>
   <div class='feature-card'>
     <div class='feature-icon'>&#x1F680;</div>
     <div class='feature-content'>
       <h3>Hexagonal Architecture</h3>
       <p>Hexagonal/Ports-and-Adapters con DDD aplicado en cada capa del proyecto.</p>
     </div>
   </div>
   <div class='feature-card'>
     <div class='feature-icon'>&#x26A1;</div>
     <div class='feature-content'>
       <h3>Event-Driven</h3>
       <p>Kafka + Avro + Outbox Pattern para mensajería asíncrona confiable.</p>
     </div>
   </div>
   <div class='feature-card'>
     <div class='feature-icon'>&#x1F9E1;</div>
     <div class='feature-content'>
       <h3>Distributed SAGAs</h3>
       <p>Patrón Saga para transacciones distribuidas con compensación automática.</p>
     </div>
   </div>
   <div class='feature-card'>
     <div class='feature-icon'>&#x1F9EA;</div>
     <div class='feature-content'>
       <h3>BDD Testing</h3>
       <p>Cucumber + Testcontainers + Allure para A/TDD de extremo a extremo.</p>
     </div>
   </div>
   <div class='feature-card'>
     <div class='feature-icon'>&#x1F4E6;</div>
     <div class='feature-content'>
       <h3>Maven BOM</h3>
       <p>Parent POM gestionado con dependencias versionadas centralizadamente.</p>
     </div>
   </div>
   <div class='feature-card'>
     <div class='feature-icon'>&#x2699;</div>
     <div class='feature-content'>
       <h3>Operabilidad</h3>
       <p>Logging ELK, Sleuth tracing, Docker, JIB, Multi-arch images.</p>
     </div>
   </div>
</div>

---

## ¿Qué es lg5-spring?

**lg5-spring** es un **Framework BOM (Bill of Materials)** de Lg Pentagon (LgLabs) diseñado para crear microservicios Spring Boot de forma rápida y estandarizada.

Es un conjunto de librerías, plantillas y estándares que te permiten enfocarte en el **dominio de negocio** en lugar de perder tiempo configurando dependencias, plugins de build, logging, testing, y patrones de arquitectura.

### Stack Técnico

| Componente | Versión |
|---|---|
| Spring Boot | 3.5.14 |
| Spring Framework | 6.2.2 |
| JDK | 21 |
| Kotlin | 2.2.0 |
| Gradle | 8.x (Kotlin DSL) |
| PostgreSQL | 42.7.5 |
| Kafka | spring-kafka 3.3.2 + Avro 1.12.0 |
| Testcontainers | 1.20.4 |
| Cucumber | 7.21.1 |
| MapStruct | 1.6.3 |

### Características Principales

| Característica | Descripción |
|---|---|
| **Arquitectura Hexagonal** | Ports-and-Adapters con DDD aplicado en cada capa |
| **Event-Driven** | Kafka + Avro + Outbox Pattern para mensajería asíncrona |
| **Sagas Distribuidas** | Compensación automática para transacciones distribuidas |
| **Testing E2E** | Unit → Integration → Acceptance (Cucumber BDD) |
| **Maven BOM** | Parent POM con dependencias versionadas centralizadamente |
| **El Operabilidad** | Logging ELK, Sleuth tracing, Docker images con JIB |

### Módulos del Framework

El framework está organizado en **~20 submódulos Gradle** que cubren todas las necesidades de un microservicio:

**Core:** `lg5-spring-parent`, `lg5-spring-starter`, `lg5-spring-api-rest`, `lg5-spring-data-jpa`, `lg5-spring-client`

**Messaging:** `lg5-spring-kafka` (config, producer, consumer, model), `lg5-spring-outbox`

**Patterns:** `lg5-jvm-saga`, `lg5-common` (domain, application-service)

**Tools:** `lg5-spring-logger`, `lg5-spring-utils`, `lg5-jvm-utils`

**Testing:** `lg5-spring-testcontainers`, `lg5-spring-integration-test`, `lg5-spring-acceptance-test`, `lg5-jvm-unit-test`

### Licencia

Distribuido bajo la [Licencia MIT](https://github.com/lg-labs-pentagon/lg5-spring/blob/main/LICENSE).
