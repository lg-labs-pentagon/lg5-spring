# lg5-spring-outbox

Es el módulo que implementa el **Outbox Pattern** para garantizar la entrega confiable de eventos a Kafka.

---

## Propósito

Garantiza que cada cambio en tu base de datos quede reflejado en un evento Kafka, evitando inconsistencias entre tu DB y tus eventos.

---

## Componentes

### OutboxStatus Enum

```kotlin
enum class OutboxStatus {
    STARTED,
    COMPLETED,
    FAILED
}
```

### OutboxScheduler Interface

```kotlin
interface OutboxScheduler {
    fun processOutboxMessage()
}
```

### SchedulerConfig

```kotlin
@Configuration
@EnableScheduling
open class SchedulerConfig { ... }
```

---

## Patrón de Uso

1. Tu servicio inserta un registro en la tabla outbox con STARTED
2. OutboxScheduler (scheduled task) busca registros STARTED
3. KafkaMessageHelper.getKafkaCallback() actualiza el estado a COMPLETED o FAILED

### Ejemplo

```java
@Component
public class MyOutboxScheduler implements OutboxScheduler {
    
    @Scheduled(fixedRate = 5000)
    @Override
    public void processOutboxMessage() {
        List<OutboxMessage> pending = repo.findByStatus(STARTED);
        pending.forEach(msg -> {
            kafkaTemplate.send(msg.getTopic(), msg.getPayload());
            repo.markCompleted(msg.getId());
        });
    }
}
```

---

## Uso

```xml
<dependency>
    <groupId>com.lg5.spring.outbox</groupId>
    <artifactId>lg5-spring-outbox</artifactId>
</dependency>
```
