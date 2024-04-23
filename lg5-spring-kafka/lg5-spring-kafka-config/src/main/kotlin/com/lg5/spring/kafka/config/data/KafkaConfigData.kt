package com.lg5.spring.kafka.config.data


import lombok.Getter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Getter
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
open class KafkaConfigData {
    private var bootstrapServers: String? = null
    private val schemaRegistryUrlKey: String? = null
    private val schemaRegistryUrl: String? = null
    private val numOfPartitions: Int? = null
    private val replicationFactor: Short? = null

    override fun toString(): String {
        return "KafkaConfigData(bootstrapServers=$bootstrapServers, schemaRegistryUrlKey=$schemaRegistryUrlKey, schemaRegistryUrl=$schemaRegistryUrl, numOfPartitions=$numOfPartitions, replicationFactor=$replicationFactor)"
    }


}