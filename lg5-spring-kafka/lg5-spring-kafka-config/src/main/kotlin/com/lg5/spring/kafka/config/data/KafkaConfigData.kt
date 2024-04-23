package com.lg5.spring.kafka.config.data

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka-config")
open class KafkaConfigData {
    @Value("\${kafka-config.bootstrap-servers}")
    lateinit var bootstrapServers: String

    @Value("\${kafka-config.schema-registry-url-key}")
    lateinit var schemaRegistryUrlKey: String

    @Value("\${kafka-config.schema-registry-url}")
    lateinit var schemaRegistryUrl: String

    @Value("\${kafka-config.num-of-partitions}")
    var numOfPartitions: Int = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }

    @Value("\${kafka-config.replication-factor}")
    var replicationFactor = 0
        get() {
            return field
        }
        set(value) {
            field = value
        }

}
