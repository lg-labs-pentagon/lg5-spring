# Paso 4: REST API

## 4.1: Controller

```java
// BlankController.java
package com.blanksystem.blank.service.api.rest;

import com.blanksystem.blank.service.domain.dto.create.CreateBlankCommand;
import com.blanksystem.blank.service.domain.dto.create.CreateBlankResponse;
import com.blanksystem.blank.service.domain.ports.input.service.BlankApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/blank", produces = "application/vnd.api.v1+json")
public class BlankController {

     private final BlankApplicationService blankApplicationService;

     public BlankController(BlankApplicationService service) {
         this.blankApplicationService = service;
      }

     @PostMapping
     public ResponseEntity<CreateBlankResponse> addBlank(
         @RequestBody CreateBlankCommand command
     ) {
         log.info("Creating blank with id: {}", command.id());
         CreateBlankResponse response = blankApplicationService.createBlank(command);
         log.info("Blank with id: {} was created", response.id());
         return ResponseEntity.accepted()
             .header(HttpHeaders.LOCATION, "/blank/" + response.id())
             .body(response);
      }
}
```

## 4.2: Response

```
POST /blank
Content-Type: application/json

{
     "id": "550e8400-e29b-41d4-a716-446655440000"
}

→ 202 Accepted
Location: /blank/550e8400-e29b-41d4-a716-446655440000

{
     "id": "550e8400-e29b-41d4-a716-446655440000",
     "message": "Blank saved successfully!"
}
```

## 4.3: Error Handling

Con `GlobalExceptionHandler` de `lg5-spring-api-rest`:

### 400 Bad Request (Validación fallida)

```
POST /blank
{
     "id": null
}

→ 400 Bad Request

{
     "code": "Validation Error",
     "message": "must not be null"
}
```

### 500 Internal Server Error

```
→ 500 Internal Server Error

{
     "code": "Internal Server Error",
     "message": "Unexpected error!"
}
```

## 4.4: application.yaml

```yaml
# blank-container/src/main/resources/application.yaml
spring:
     application:
         name: blank-service

blanksystem:
     blank:
         service:
             blank-topic-name: blank.1.0.event.created
         events:
             journal:
                 blank:
                     topic: blank.1.0.event.created
                     consumer:
                         group: blank-topic-consumer

kafka-config:
     bootstrap-servers: localhost:9092,localhost:9093,localhost:9094
     schema-registry-url-key: schema.registry.url
     schema-registry-url: http://localhost:8081
     num-of-partitions: 3
     replication-factor: 3

kafka-producer-config:
     key-serializer-class: org.apache.kafka.common.serialization.StringSerializer
     value-serializer-class: io.confluent.kafka.serializers.KafkaAvroSerializer
     compression-type: snappy
     acks: all

kafka-consumer-config:
     key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
     value-deserializer: io.confluent.kafka.serializers.KafkaAvroDeserializer
     auto-offset-reset: earliest
     specific-avro-reader: true
     batch-listener: true
     concurrency-level: 3
```

## 4.5: Docker Compose (Infraestructura)

```bash
# Start Postgres + Kafka
make docker-up

# Start APP
make run-app

# Access points
# API:         http://localhost:8181
# PgAdmin:     http://localhost:5013  (blanksystem@db.com / blanksystem-db)
# Kafka UI:    http://localhost:9080
```
