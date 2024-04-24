package com.lg5.spring.kafka.config.data

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "kafka-config")
open class KafkaConfigData {

    lateinit var bootstrapServers: String
    lateinit var schemaRegistryUrlKey: String
    lateinit var schemaRegistryUrl: String
    var numOfPartitions: Int = 0
    var replicationFactor: Short = 0
}
