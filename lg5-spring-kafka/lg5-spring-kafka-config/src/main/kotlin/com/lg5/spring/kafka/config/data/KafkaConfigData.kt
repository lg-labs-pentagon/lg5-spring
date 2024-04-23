package com.lg5.spring.kafka.config.data



import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "kafka-config")
open class KafkaConfigData(
    @Value("\${spring.kafka.bootstrap-servers}")
    var bootstrapServers: String? = null,
    var schemaRegistryUrlKey: String? = null,
    val schemaRegistryUrl: String? = null,
    val numOfPartitions: Int? = null,
    val replicationFactor: Short? = null
)
