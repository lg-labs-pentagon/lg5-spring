# KafkaProducer API

## Interfaz

```java
public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
    void send(String topicName, K key, V message,
              BiConsumer<SendResult<K, V>, Throwable> callback);
}
```

### Métodos

| Método | Parámetros | Retorno | Descripción |
|--------|-----------|---------|-------------|
| `send()` | `topicName`, `key`, `message`, `callback` | `void` | Envía mensaje a Kafka |

### Parámetros

| Parámetro | Tipo | Descripción |
|-----------|------|-------------|
| `topicName` | String | Topic de Kafka |
| `key` | `K` | Key del mensaje (serializable) |
| `message` | `V` | Payload (Avro SpecificRecordBase) |
| `callback` | `BiConsumer` | Callback para SUCCESS/FAILED |

### Ejemplo de Uso

```java
@Component
public class BlankEventPublisher implements BlMessagePublisher {
    @Autowired KafkaProducer<String, BlankAvroModel> producer;

    @Override
    public void publish(BlankCreatedEvent event) {
        BlankAvroModel model = mapper.toAvro(event);
        producer.send(
            "blank.1.0.event.created",
            model.getId(),
            model,
            kafkaMessageHelper.getCallback(model.getId(), model)
        );
    }
}
```

---

Ver también: [lg5-spring-kafka documentation](/core/spring-kafka)
