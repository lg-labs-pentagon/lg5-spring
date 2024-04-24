package com.lg5.spring.kafka.config.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-consumer-config")
open class KafkaConsumerConfigData {

    @Value("\${kafka-consumer-config.key-deserializer}")
    lateinit var keyDeserializer: String
    @Value("\${kafka-consumer-config.value-deserializer}")
    lateinit var valueDeserializer: String
    @Value("\${kafka-consumer-config.auto-offset-reset}")
    lateinit var autoOffsetReset: String
    @Value("\${kafka-consumer-config.specific-avro-readerKey}")
    lateinit var specificAvroReaderKey: String
    @Value("\${kafka-consumer-config.specific-avro-reader}")
    lateinit var specificAvroReader: String
    @Value("\${kafka-consumer-config.batch-listener}")
    var batchListener: Boolean = false
    @Value("\${kafka-consumer-config.auto-startup}")
    var autoStartup: Boolean =  false
    @Value("\${kafka-consumer-config.concurrency-level}")
    var concurrencyLevel: Int =0
    @Value("\${kafka-consumer-config.session-timeout-ms}")
    var sessionTimeoutMs: Int =0
    @Value("\${kafka-consumer-config.heartbeat-interval-ms}")
    var heartbeatIntervalMs: Int=0
    @Value("\${kafka-consumer-config.max-poll-interval-ms}")
    var maxPollIntervalMs: Int=0
    @Value("\${kafka-consumer-config.poll-timeout-ms}")
    var pollTimeoutMs: Long =0
    @Value("\${kafka-consumer-config.max-poll-records}")
    var maxPollRecords: Int=0
    @Value("\${kafka-consumer-config.max-partition-fetch-bytes-default}")
    var maxPartitionFetchBytesDefault: Int =0
    @Value("\${kafka-consumer-config.max-partition-fetch-bytes-boost-factor}")
    val maxPartitionFetchBytesBoostFactor: Int = 0

}
