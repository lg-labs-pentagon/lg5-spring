# Paso 6: Kafka Messaging

## 6.1: Avro Model

Schema Avro para mensajes:

```json
// blank-message-model/src/main/resources/avro/blank.avsc
{
   "namespace": "com.blanksystem.blank.service.message.model.avro",
   "type": "record",
   "name": "BlankAvroModel",
   "fields": [
     { "name": "id", "type": "string" }
   ]
}
```

## 6.2: Kafka Publisher

```java
// BlankEventKafkaPublisher.java
package com.blanksystem.blank.service.message.publisher.kafka;

import com.blanksystem.blank.service.domain.config.BlankServiceConfigData;
import com.blanksystem.blank.service.domain.entity.Blank;
import com.blanksystem.blank.service.domain.event.BlankCreatedEvent;
import com.blanksystem.blank.service.domain.valueobject.BlankId;
import com.blanksystem.blank.service.message.mapper.BlankMessagingDataMapper;
import com.blanksystem.blank.service.message.model.avro.BlankAvroModel;
import com.lg5.spring.kafka.producer.KafkaMessageHelper;
import com.lg5.spring.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BlankEventKafkaPublisher implements BlankMessagePublisher {

     private final BlankMessagingDataMapper mapper;
     private final KafkaProducer<String, BlankAvroModel> kafkaProducer;
     private final BlankServiceConfigData configData;
     private final KafkaMessageHelper kafkaMessageHelper;

     @Override
     public void publish(BlankCreatedEvent blankCreatedEvent) {
         Blank blank = blankCreatedEvent.getBlank();
         BlankId blankId = blank.getId();
         log.info("Received BlankCreatedEvent for blank id: {}", blankId.getValue());

         try {
             BlankAvroModel blankAvroModel =
                 mapper.blankCreatedEventToBlankAvroModel(blankCreatedEvent);

             kafkaProducer.send(
                 configData.getTopic(),
                 blankAvroModel.getId(),
                 blankAvroModel,
                 kafkaMessageHelper.getCallback(
                     blankAvroModel.getId(),
                     blankAvroModel
                 )
             );
             log.info("BlankCreatedEvent sent to kafka for blank id: {}",
                 blankAvroModel.getId());
          } catch (Exception e) {
             log.error("Error sending BlankCreatedEvent to kafka: {}",
                 e.getMessage());
          }
      }
}
```

## 6.3: Kafka Listener

```java
// BlankKafkaListener.java
package com.blanksystem.blank.service.message.listener.kafka;

import com.blanksystem.blank.service.domain.exception.BlankApplicationServiceException;
import com.blanksystem.blank.service.domain.ports.input.message.listener.blank.BlankMessageListener;
import com.blanksystem.blank.service.message.mapper.BlankMessagingDataMapper;
import com.blanksystem.blank.service.message.model.avro.BlankAvroModel;
import com.lg5.spring.kafka.consumer.KafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class BlankKafkaListener implements KafkaConsumer<BlankAvroModel> {

     private final BlankMessageListener blankMessageListener;
     private final BlankMessagingDataMapper mapper;

     public BlankKafkaListener(
         BlankMessageListener listener,
         BlankMessagingDataMapper mapper
     ) {
         this.blankMessageListener = listener;
         this.mapper = mapper;
      }

     @KafkaListener(
         id = "${blanksystem.blank.events.journal.blank.consumer.group}",
         topics = "${blanksystem.blank.events.journal.blank.topic}"
     )
     @Override
     public void receive(
         List<BlankAvroModel> messages,
         List<String> keys,
         List<Integer> partitions,
         List<Long> offsets
     ) {
         log.info("{} number of blank create messages received", messages.size());
         messages.forEach(blankAvroModel -> {
             try {
                 blankMessageListener.blankCreated(
                     mapper.blankAvroModelToBlankModel(blankAvroModel)
                 );
              } catch (Exception e) {
                 throw new BlankApplicationServiceException(
                     "Error processing blank: " + e.getMessage(), e);
              }
          });
      }
}
```

## 6.4: Message Listener (Input Port)

```java
// BlankMessageListener.java
package com.blanksystem.blank.service.domain.ports.input.message.listener.blank;

import com.blanksystem.blank.service.domain.dto.message.BlankModel;
import java.util.function.Consumer;

public interface BlankMessageListener {
     void blankCreated(BlankModel blankModel);
}
```

## 6.5: Flow Completo Kafka

```
┌──────────────┐      Outbox       ┌──────────────┐
│              │  ──────────────▶  │              │
│  REST POST   │                    │  Kafka       │
│  /blank      │  DB Save          │  Topic:      │
│              │                    │  blank.1.0   │
└──────────────┘                    └──────────────┘
                                            │
                                            │ Kafka
                                            ▼
┌──────────────┐      ┌──────────────┐     ┌──────────────┐
│              │      │              │     │              │
│  3rd System  │◀──── │  Event       │◀── │  blank-      │
│              │      │  Processing  │     │  service     │
│              │      │              │     │  (listener)   │
└──────────────┘      └──────────────┘     └──────────────┘
```
