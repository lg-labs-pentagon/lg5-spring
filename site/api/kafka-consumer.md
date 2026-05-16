# KafkaConsumer API

## Interfaz

```java
public interface KafkaConsumer<T extends SpecificRecordBase> {
    void receive(List<T> messages, List<String> keys,
                 List<Integer> partitions, List<Long> offsets);
}
```

### Métodos

| Método | Parámetros | Retorno | Descripción |
|--------|-----------|---------|-------------|
| `receive()` | `messages`, `keys`, `partitions`, `offsets` | `void` | Recibe mensajes batch |

## Ejemplo de Implementación

```java
@Service
public class BlankKafkaListener implements KafkaConsumer<BlankAvroModel> {
    @Autowired BlankMessageListener listener;
    @Autowired BlankMessagingDataMapper mapper;

    @KafkaListener(
        id = "${blanksystem.blank.events.journal.blank.consumer.group}",
        topics = "${blanksystem.blank.events.journal.blank.topic}"
    )
    @Override
    public void receive(List<BlankAvroModel> messages, List<String> keys,
                        List<Integer> partitions, List<Long> offsets) {
        messages.forEach(msg -> {
            try {
                listener.blankCreated(mapper.toDomain(msg));
            } catch (Exception e) {
                throw new BlankApplicationServiceException(
                    "Error processing blank: " + e.getMessage(), e);
            }
        });
    }
}
```

---

Ver también: [lg5-spring-kafka documentation](/core/spring-kafka)
