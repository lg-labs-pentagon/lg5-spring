# lg5-spring-kafka

Es el subsistema completo de mensajería asíncrona con Kafka y Avro.

---

## Estructura del Paquete

```
lg5-spring-kafka/
├── lg5-spring-kafka-config/       # ConfigurationData
├── lg5-spring-kafka-model/        # Apache Avro
├── lg5-spring-kafka-producer/     # KafkaProducer impl
└── lg5-spring-kafka-consumer/     # KafkaConsumer impl
```

---

## 1. lg5-spring-kafka-config

### KafkaConfigData

```kotlin
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
open class KafkaConfigData {
    lateinit var bootstrapServers: String
    lateinit var schemaRegistryUrlKey: String
    lateinit var schemaRegistryUrl: String
    var numOfPartitions: Int = 0
    var replicationFactor: Short = 0
}
```

### KafkaProducerConfigData

```kotlin
@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
open class KafkaProducerConfigData {
    lateinit var keySerializerClass: String
    lateinit var valueSerializerClass: String
    lateinit var compressionType: String
    lateinit var acks: String
    var batchSize: Int = 0
}
```

### KafkaConsumerConfigData

```kotlin
@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
open class KafkaConsumerConfigData {
    lateinit var keyDeserializer: String
    lateinit var valueDeserializer: String
    lateinit var autoOffsetReset: String
    var batchListener: Boolean = false
    var concurrencyLevel: Int = 0
}
```

---

## 2. Valores por Default

```yaml
kafka-config:
    bootstrap-servers: localhost:9092, localhost:9093, localhost:9094
    schema-registry-url: http://localhost:8081
    num-of-partitions: 3
    replication-factor: 3

kafka-producer-config:
    key-serializer-class: StringSerializer
    value-serializer-class: KafkaAvroSerializer
    compression-type: snappy
    acks: all

kafka-consumer-config:
    key-deserializer: StringDeserializer
    value-deserializer: KafkaAvroDeserializer
    auto-offset-reset: earliest
    batch-listener: true
    concurrency-level: 3
```

---

## 3. Ejemplo de Uso

```xml
<dependency>
    <groupId>lg5.common.kafka</groupId>
    <artifactId>lg5-spring-kafka-config</artifactId>
</dependency>
<dependency>
    <groupId>lg5.common.kafka</groupId>
    <artifactId>lg5-spring-kafka-producer</artifactId>
</dependency>
<dependency>
    <groupId>lg5.common.kafka</groupId>
    <artifactId>lg5-spring-kafka-consumer</artifactId>
</dependency>
```
