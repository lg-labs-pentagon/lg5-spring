package com.lg5.spring.kafka.publisher.service.impl

import com.lg5.spring.kafka.publisher.exception.KafkaProducerException
import com.lg5.spring.kafka.publisher.service.KafkaProducerV2

import jakarta.annotation.PreDestroy
import org.apache.avro.specific.SpecificRecordBase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.KafkaException
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component

import java.io.Serializable
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

@Component
class KafkaProducerV2Impl<K : Serializable, V : SpecificRecordBase>(kvKafkaTemplate: KafkaTemplate<K, V>) :
    KafkaProducerV2<K, V> {
    private val kafkaTemplate = kvKafkaTemplate

    override fun send(topicName: String, key: K, message: V, callback: BiConsumer<SendResult<K, V>, Throwable>) {
        LOG.info("Sending message={} to topic {}", message, topicName)
        try {
            val kafkaResultFuture:CompletableFuture<SendResult<K, V>> = kafkaTemplate.send(topicName, key, message)
            kafkaResultFuture.whenComplete(callback)
        } catch (e: KafkaException) {
            LOG.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message, e.message)
            throw KafkaProducerException("Error on kafka producer with key: $key and message: $message")
        }
    }

    @PreDestroy
    fun close() {
        LOG.info("Closing kafka producer!")
        kafkaTemplate.destroy()
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(KafkaProducerV2Impl::class.java)
    }
}
