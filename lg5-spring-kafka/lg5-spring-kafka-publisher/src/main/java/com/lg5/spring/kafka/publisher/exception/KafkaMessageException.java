package com.lg5.spring.kafka.publisher.exception;

public class KafkaMessageException extends RuntimeException {
    public KafkaMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
