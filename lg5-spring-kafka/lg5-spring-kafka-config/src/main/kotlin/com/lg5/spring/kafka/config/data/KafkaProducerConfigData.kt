package com.lg5.spring.kafka.config.data

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
open class KafkaProducerConfigData {

    lateinit var keySerializerClass: String
    lateinit var valueSerializerClass: String
    lateinit var compressionType: String
    lateinit var acks: String
    var batchSize: Int = 0
    var batchSizeBoostFactor: Int = 0
    var lingerMs: Int = 0
    var requestTimeoutMs: Int = 0
    var retryCount: Int = 0
}
