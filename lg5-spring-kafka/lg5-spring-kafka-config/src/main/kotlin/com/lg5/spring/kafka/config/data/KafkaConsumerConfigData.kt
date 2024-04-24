package com.lg5.spring.kafka.config.data

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
open class KafkaConsumerConfigData {
    lateinit var keyDeserializer: String
    lateinit var valueDeserializer: String
    lateinit var autoOffsetReset: String
    lateinit var specificAvroReaderKey: String
    lateinit var specificAvroReader: String // TODO change and test as Bool
    var batchListener: Boolean = false
    var autoStartup: Boolean = false
    var concurrencyLevel: Int = 0
    var sessionTimeoutMs: Int = 0
    var heartbeatIntervalMs: Int = 0
    var maxPollIntervalMs: Int = 0
    var pollTimeoutMs: Long = 0
    var maxPollRecords: Int = 0
    var maxPartitionFetchBytesDefault: Int = 0
    var maxPartitionFetchBytesBoostFactor: Int = 0
}
