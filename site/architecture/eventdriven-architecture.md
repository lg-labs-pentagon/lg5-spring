# Event-Driven Architecture

## Arquitectura EventDriven

lg5-spring soporta **Event-Driven Architecture (EDA)** con:
- **Kafka** para mensajería distribuida
- **Avro** para schemas tipados
- **Outbox Pattern** para eventos confiables
- **Sagas** para transacciones distribuidas

## Componentes Clave

### 1. Kafka Producer

```java
@Component
public class MyEventPublisher implements MyMessagePublisher {

      @Override
     public void publish(MyEvent event) {
         MyAvroModel model = mapper.toAvro(event);
         kafkaProducer.send(
             configData.getTopic(),
             model.getId(),
             model,
             kafkaMessageHelper.getCallback(model.getId(), model)
         );
      }
}
```

### 2. Kafka Consumer

```java
@Component
public class MyKafkaListener implements KafkaConsumer<MyAvroModel> {

      @KafkaListener(
          id = "${my.kafka.consumer.group}",
          topics = "${my.kafka.topic}"
      )
     @Override
     public void receive(List<MyAvroModel> messages,
                         List<String> keys,
                         List<Integer> partitions,
                         List<Long> offsets) {
          messages.forEach(msg -> {
             listener.onEvent(mapper.toDomain(msg));
         });
      }
}
```

### 3. Outbox Pattern

```java
// OutboxScheduler - Scheduled task que lee eventos pendientes
@Scheduled(fixedRate = 5000)
public void processOutboxMessage() {
     List<OutboxMessage> pending = outboxRepo.findByStatus(STARTED);
     pending.forEach(msg -> {
         kafkaTemplate.send(msg.getTopic(), msg.getPayload());
         outboxRepo.markCompleted(msg.getId());
     });
}
```

### 4. SAGA Pattern

```java
public void processSaga(MyEvent event) {
     SagaContext ctx = new SagaContext(event);
     try {
         step1.process(ctx);
         step2.process(ctx);
         step3.process(ctx);
     } catch (Exception e) {
          // Rollback in reverse order
          step3.rollback(ctx);
          step2.rollback(ctx);
          step1.rollback(ctx);
      }
}
```

## Patrón de Flujo de Eventos

```
┌──────────┐    ┌──────────┐    ┌──────────┐
│          │    │          │    │          │
│   API    │    │  DB      │    │  Kafka   │
│  POST    │──▶ │  Save    │──▶ │  Outbox  │
│          │    │          │    │  →       │
│          │    │          │    │           │
└──────────┘    └──────────┘    └──────────┘
                                              │
                                              ▼
┌──────────┐    ┌──────────┐    ┌──────────┐
│          │    │          │    │          │
│  Service │    │  Kafka   │    │          │
│  B       │◀── │  Topic   │◀── │  Service │
│          │    │          │    │  A       │
│          │    │          │    │          │
└──────────┘    └──────────┘    └──────────┘
```
