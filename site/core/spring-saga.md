# lg5-jvm-saga

Implementa el patrón **Saga** para transacciones distribuidas entre microservicios.

---

## Propósito

Permite orquestar secuencias de operaciones que involucran múltiples servicios, con **compensación automática** si algo falla.

---

## Arquitectura

Es puramente una interfaz Kotlin sin dependencias externas — diseñada para ser combinada con otros módulos como `lg5-spring-kafka`.

---

## Dependency

```kotlin
// lg5-jvm-saga/build.gradle.kts
// Sin dependencias externas - pure Kotlin interface
```

---

## SagaStep Interface

```kotlin
interface SagaStep<T> {
    fun process(data: T)    // Forward execution
    fun rollback(data: T)   // Compensation / rollback
}
```

---

## Conceptos Clave

| Concepto | Descripción |
|----------|-------------|
| process(data) | Ejecución forward (acción normal) |
| rollback(data) | Compensación (acción inversa si falla) |
| T | Tipo genérico de datos que fluyen por la saga |

---

## Ejemplo de Uso

```java
public class CreateOrderSaga implements SagaStep<OrderContext> {
    
    @Override
    public void process(OrderContext ctx) {
        orderRepo.create(ctx.getOrder());
        paymentService.charge(ctx.getPayment());
        inventoryService.reserve(ctx.getItems());
    }

    @Override
    public void rollback(OrderContext ctx) {
        inventoryService.release(ctx.getItems());
        paymentService.refund(ctx.getPayment());
        orderRepo.delete(ctx.getOrder().getId());
    }
}
```

---

## Uso en tu Servicio

```xml
<dependency>
    <groupId>com.lg5.jvm</groupId>
    <artifactId>lg5-jvm-saga</artifactId>
</dependency>
```
