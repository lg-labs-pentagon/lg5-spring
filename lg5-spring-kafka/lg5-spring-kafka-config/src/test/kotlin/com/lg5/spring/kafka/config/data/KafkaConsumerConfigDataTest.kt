package com.lg5.spring.kafka.config.data

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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
@EnableConfigurationProperties(value = [KafkaConsumerConfigData::class])
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
class KafkaConsumerConfigDataTest {

    @Autowired
    lateinit var consumerConfigData: KafkaConsumerConfigData

    @Test
    fun it_should_load_properties_to_a_kafka_consumer_config() {
        assertEquals("org.apache.kafka.common.serialization.StringDeserializerTest", consumerConfigData.keyDeserializer)
        assertEquals("io.confluent.kafka.serializers.KafkaAvroDeserializer", consumerConfigData.valueDeserializer)
        assertEquals("earliest", consumerConfigData.autoOffsetReset)
        assertEquals("specific.avro.reader", consumerConfigData.specificAvroReaderKey)
        assertEquals("true", consumerConfigData.specificAvroReader)
        assertTrue(consumerConfigData.batchListener)
        assertTrue(consumerConfigData.autoStartup)
        assertEquals(3, consumerConfigData.concurrencyLevel)
        assertEquals(10000, consumerConfigData.sessionTimeoutMs)
        assertEquals(3000, consumerConfigData.heartbeatIntervalMs)
        assertEquals(300000, consumerConfigData.maxPollIntervalMs)
        assertEquals(500, consumerConfigData.maxPollRecords)
        assertEquals(1048576, consumerConfigData.maxPartitionFetchBytesDefault)
        assertEquals(1, consumerConfigData.maxPartitionFetchBytesBoostFactor)
        assertEquals(150, consumerConfigData.pollTimeoutMs)


    }

}