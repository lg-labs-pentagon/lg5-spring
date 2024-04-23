package com.lg5.spring.kafka.config.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-config")
open class KafkaConfigData {
    @Value("\${bootstrap-servers}")
    lateinit var bootstrapServers: String

    @Value("\${schema-registry-url-key}")
    lateinit var schemaRegistryUrlKey: String

    @Value("\${schema-registry-url}")
    lateinit var schemaRegistryUrl: String

    @Value("\${num-of-partitions}")
    var numOfPartitions: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }

    @Value("\${replication-factor}")
    var replicationFactor = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }

}
