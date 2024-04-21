package com.lg5.spring.kafka.config.data

import lombok.Data
import lombok.Getter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Getter
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
open class KafkaConfigData {
    private val bootstrapServers: String? = null
    private val schemaRegistryUrlKey: String? = null
    private val schemaRegistryUrl: String? = null
    private val numOfPartitions: Int? = null
    private val replicationFactor: Short? = null
}