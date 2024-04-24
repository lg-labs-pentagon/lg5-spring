package com.lg5.spring.kafka.config.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-consumer-config")
class KafkaConsumerConfigData {
    lateinit var keyDeserializer: String

    lateinit var valueDeserializer: String

    lateinit var autoOffsetReset: String

    lateinit var specificAvroReaderKey: String

    lateinit var specificAvroReader: String

    var batchListener: Boolean = false
    var autoStartup: Boolean =  false

    var concurrencyLevel: Int =0

    lateinit var sessionTimeoutMs:String
    lateinit var heartbeatIntervalMs:String
    lateinit var maxPollIntervalMs:String
    lateinit var pollTimeoutMs:String
    lateinit var maxPollRecords:String
    var maxPartitionFetchBytesDefault:Int=0
    var maxPartitionFetchBytesBoostFactor:Int=0

}
