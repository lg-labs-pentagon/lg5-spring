package com.lg5.spring.kafka.publisher

import com.lg5.spring.kafka.config.data.KafkaConfigData
import com.lg5.spring.kafka.config.data.KafkaProducerConfigData
import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.io.Serializable


open class KafkaProducerConfigV2<K : Serializable?, V : SpecificRecordBase?>(
    private val kafkaConfigData: KafkaConfigData,
    private val kafkaProducerConfigData: KafkaProducerConfigData
) {

    open fun producerConfigV2(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaConfigData.bootstrapServers
        props[kafkaConfigData.schemaRegistryUrlKey] = kafkaConfigData.schemaRegistryUrl
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = kafkaProducerConfigData.keySerializerClass
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = kafkaProducerConfigData.valueSerializerClass
        props[ProducerConfig.BATCH_SIZE_CONFIG] = (kafkaProducerConfigData.batchSize
                * kafkaProducerConfigData.batchSizeBoostFactor)
        props[ProducerConfig.LINGER_MS_CONFIG] = kafkaProducerConfigData.lingerMs
        props[ProducerConfig.COMPRESSION_TYPE_CONFIG] = kafkaProducerConfigData.compressionType
        props[ProducerConfig.ACKS_CONFIG] = kafkaProducerConfigData.acks
        props[ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG] = kafkaProducerConfigData.requestTimeoutMs
        props[ProducerConfig.RETRIES_CONFIG] = kafkaProducerConfigData.retryCount
        return props
    }


    open fun producerFactoryV2(): ProducerFactory<K, V> {
        return DefaultKafkaProducerFactory(producerConfigV2())
    }


    open fun kafkaTemplateV2(): KafkaTemplate<K, V> {
        return KafkaTemplate(producerFactoryV2())
    }
}