package com.lg5.spring.kafka.config.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-consumer-config")
open class KafkaConsumerConfigData {

    @Value("\${key-deserializer}")
    lateinit var keyDeserializer: String
    @Value("\${value-deserializer}")
    lateinit var valueDeserializer: String
    @Value("\${auto-offset-reset}")
    lateinit var autoOffsetReset: String
    @Value("\${specific-avro-readerKey}")
    lateinit var specificAvroReaderKey: String
    @Value("\${specific-avro-reader}")
    lateinit var specificAvroReader: String
    @Value("\${batch-listener}")
    var batchListener: Boolean = false
    @Value("\${auto-startup}")
    var autoStartup: Boolean =  false
    @Value("\${concurrency-level}")
    var concurrencyLevel: Int =0
    @Value("\${session-timeout-ms}")
    var sessionTimeoutMs: Int =0
    @Value("\${heartbeat-interval-ms}")
    var heartbeatIntervalMs: Int=0
    @Value("\${max-poll-interval-ms}")
    var maxPollIntervalMs: Int=0
    @Value("\${poll-timeout-ms}")
    var pollTimeoutMs: Long =0
    @Value("\${max-poll-records}")
    var maxPollRecords: Int=0
    @Value("\${max-partition-fetch-bytes-default}")
    var maxPartitionFetchBytesDefault: Int =0
    @Value("\${max-partition-fetch-bytes-boost-factor}")
    val maxPartitionFetchBytesBoostFactor: Int = 0

}
