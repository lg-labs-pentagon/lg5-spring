package com.lg5.spring.kafka.producer.exception;

public class KafkaMessageException extends RuntimeException {
    public KafkaMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
