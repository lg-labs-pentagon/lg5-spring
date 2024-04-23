package com.lg5.spring.kafka.config.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-producer-config")
open class KafkaProducerConfigData {

    @Value("\${key-serializer-class}")
    lateinit var keySerializerClass: String

    @Value("\${value-serializer-class}")
    lateinit var valueSerializerClass: String

    @Value("\${compression-type}")
    lateinit var compressionType: String

    @Value("\${acks}")
    lateinit var acks: String

    @Value("\${batch-size}")
    var batchSize: Int=0

    @Value("\${batch-size-boost-factor}")
    var batchSizeBoostFactor: Int = 0

    @Value("\${linger-ms}")
    var lingerMs: Int = 0

    @Value("\${request-timeout-ms}")
    var requestTimeoutMs: Int = 0

    @Value("\${retry-count}")
    var retryCount: Int = 0
}
