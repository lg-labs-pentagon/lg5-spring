package com.lg5.spring.kafka.producer.exception;

public class KafkaProducerException extends RuntimeException {
    public KafkaProducerException(String message, Throwable cause) {
        super(message, cause);
    }

    public KafkaProducerException(String message) {
        super(message);
    }
}
