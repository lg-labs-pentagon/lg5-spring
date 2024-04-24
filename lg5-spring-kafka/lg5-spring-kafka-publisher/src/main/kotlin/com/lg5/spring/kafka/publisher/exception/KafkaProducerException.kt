package com.lg5.spring.kafka.publisher.exception

class KafkaProducerException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}