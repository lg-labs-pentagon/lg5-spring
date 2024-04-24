package com.lg5.spring.kafka.config.data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@ContextConfiguration(initializers = [ConfigDataApplicationContextInitializer::class])
@EnableConfigurationProperties(value = [KafkaConfigData::class])
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
class KafkaConfigDataTest {

    @Autowired
    lateinit var kafkaConfigData: KafkaConfigData

    @Test
    fun it_should_load_properties_to_a_kafka_config_data() {
        assertEquals("localhost:19092, localhost:29092, localhost:39092", kafkaConfigData.bootstrapServers)
        assertEquals("schema.registry.urlTest", kafkaConfigData.schemaRegistryUrlKey)
        assertEquals("http://localhost:8081", kafkaConfigData.schemaRegistryUrl)
        assertEquals(3, kafkaConfigData.numOfPartitions)
        assertEquals(3, kafkaConfigData.replicationFactor)

    }
}