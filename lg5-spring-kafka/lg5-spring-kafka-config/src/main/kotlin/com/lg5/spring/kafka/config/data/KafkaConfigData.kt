package com.lg5.spring.kafka.config.data

import com.lg5.spring.kafka.config.data.KafkaConfigData.Companion.PREFIX
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource

@AutoConfiguration
@ConfigurationProperties(PREFIX)
@PropertySource("classpath:application.yaml")
open class KafkaConfigData {

    lateinit var bootstrapServers: String
    lateinit var schemaRegistryUrlKey: String
    lateinit var schemaRegistryUrl: String
    var numOfPartitions: Int = 0
    var replicationFactor: Short = 0

    companion object{
        const val PREFIX: String = "kafka-config"
    }
}
