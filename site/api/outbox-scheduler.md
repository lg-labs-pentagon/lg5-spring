# OutboxScheduler API

## Interfaz

```kotlin
interface OutboxScheduler {
    fun processOutboxMessage()
}
```

### Métodos

| Método | Parámetros | Retorno | Descripción |
|--------|-----------|---------|-------------|
| `processOutboxMessage()` | Ninguno | `void` | Procesa mensajes pendientes de Kafka |

### Estados (OutboxStatus Enum)

| Estado | Descripción |
|--------|-------------|
| `STARTED` | Evento creado, pendiente de publicar |
| `COMPLETED` | Evento publicado exitosamente |
| `FAILED` | Error al intentar publicar |

## Ejemplo de Implementación

```java
@Component
public class MyOutboxScheduler implements OutboxScheduler {
    @Autowired OutboxRepository repo;
    @Scheduled(fixedRate = 5000)
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

Ver también: [lg5-spring-outbox documentation](/core/spring-outbox)
