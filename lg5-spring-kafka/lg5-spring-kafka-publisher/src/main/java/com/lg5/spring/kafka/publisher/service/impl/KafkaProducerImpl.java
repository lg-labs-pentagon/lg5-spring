package com.lg5.spring.kafka.publisher.service.impl;


import com.lg5.spring.kafka.publisher.exception.KafkaProducerException;
import com.lg5.spring.kafka.publisher.service.KafkaProducer;
import jakarta.annotation.PreDestroy;

import org.apache.avro.specific.SpecificRecordBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;


@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerImpl.class);

    private final KafkaTemplate<K, V> kafkaTemplate;



    public KafkaProducerImpl(KafkaTemplate<K, V> kvKafkaTemplate) {
        this.kafkaTemplate = kvKafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, BiConsumer<SendResult<K, V>, Throwable> callback) {
        LOG.info("Sending message={} to topic {}", message, topicName);
        try {
            final CompletableFuture<SendResult<K, V>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
            kafkaResultFuture.whenComplete(callback);
        } catch (KafkaException e) {
            LOG.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message, e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
        }
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            LOG.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
}
