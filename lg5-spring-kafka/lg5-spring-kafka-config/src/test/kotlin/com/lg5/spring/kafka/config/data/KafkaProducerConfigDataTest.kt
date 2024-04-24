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
@EnableConfigurationProperties(value = [KafkaProducerConfigData::class])
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
class KafkaProducerConfigDataTest {

    @Autowired
    lateinit var producerConfigData: KafkaProducerConfigData

    @Test
    fun it_should_load_properties_to_a_kafka_config_data() {
        assertEquals("org.apache.kafka.common.serialization.StringSerializer", producerConfigData.keySerializerClass)
        assertEquals("io.confluent.kafka.serializers.KafkaAvroSerializer", producerConfigData.valueSerializerClass)
        assertEquals("snappy", producerConfigData.compressionType)
        assertEquals("all", producerConfigData.acks)
        assertEquals(16384, producerConfigData.batchSize)
        assertEquals(100, producerConfigData.batchSizeBoostFactor)
        assertEquals(5, producerConfigData.lingerMs)
        assertEquals(60000, producerConfigData.requestTimeoutMs)
        assertEquals(5, producerConfigData.retryCount)

    }

}