# SagaStep API

## Interfaz

```kotlin
interface SagaStep<T> {
    fun process(data: T)   // Forward execution
    fun rollback(data: T)  // Compensation / rollback
}
```

### Métodos

| Método | Parámetro | Retorno | Descripción |
|--------|-----------|---------|-------------|
| `process()` | `data: T` | `void` | Ejecución hacia adelante (forward action) |
| `rollback()` | `data: T` | `void` | Compensación (reverse action si falla) |


### Ejemplo de Uso

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

Ver también: [lg5-jvm-saga documentation](/core/spring-saga)
