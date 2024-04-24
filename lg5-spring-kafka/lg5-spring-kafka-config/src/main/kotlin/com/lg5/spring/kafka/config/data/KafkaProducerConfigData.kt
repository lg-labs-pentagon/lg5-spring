package com.lg5.spring.kafka.config.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-producer-config")
open class KafkaProducerConfigData {

    @Value("\${kafka-producer-config.key-serializer-class}")
    lateinit var keySerializerClass: String

    @Value("\${kafka-producer-config.value-serializer-class}")
    lateinit var valueSerializerClass: String

    @Value("\${kafka-producer-config.compression-type}")
    lateinit var compressionType: String

    @Value("\${kafka-producer-config.acks}")
    lateinit var acks: String

    @Value("\${kafka-producer-config.batch-size}")
    var batchSize: Int=0

    @Value("\${kafka-producer-config.batch-size-boost-factor}")
    var batchSizeBoostFactor: Int = 0

    @Value("\${kafka-producer-config.linger-ms}")
    var lingerMs: Int = 0

    @Value("\${kafka-producer-config.request-timeout-ms}")
    var requestTimeoutMs: Int = 0

    @Value("\${kafka-producer-config.retry-count}")
    var retryCount: Int = 0
}
