package com.lg5.spring.kafka.publisher.service

import org.apache.avro.specific.SpecificRecordBase
import org.springframework.kafka.support.SendResult
import java.io.Serializable
import java.util.function.BiConsumer


interface KafkaProducer<K : Serializable, V : SpecificRecordBase> {
    fun send(topicName: String, key: K, message: V, callback: BiConsumer<SendResult<K, V>, Throwable>)
}