package com.lg5.spring.kafka.config.data

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-consumer-config")
data class KafkaConsumerConfigData(
    val keyDeserializer: String? = null,
    val valueDeserializer: String? = null,
    val autoOffsetReset: String? = null,
    val specificAvroReaderKey: String? = null,
    val specificAvroReader: String? = null,
    val batchListener: Boolean? = null,
    val autoStartup: Boolean? = null,
    val concurrencyLevel: Int? = null,
    val sessionTimeoutMs: Int? = null,
    val heartbeatIntervalMs: Int? = null,
    val maxPollIntervalMs: Int? = null,
    val pollTimeoutMs: Long? = null,
    val maxPollRecords: Int? = null,
    val maxPartitionFetchBytesDefault: Int? = null,
    val maxPartitionFetchBytesBoostFactor: Int? = null
)
