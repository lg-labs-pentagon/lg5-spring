package com.lg5.spring.kafka.config.data

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-producer-config")
data class KafkaProducerConfigData(
    val keySerializerClass: String? = null,
    val valueSerializerClass: String? = null,
    val compressionType: String? = null,
    val acks: String? = null,
    val batchSize: Int? = null,
    val batchSizeBoostFactor: Int? = null,
    val lingerMs: Int? = null,
    val requestTimeoutMs: Int? = null,
    val retryCount: Int? = null
)
