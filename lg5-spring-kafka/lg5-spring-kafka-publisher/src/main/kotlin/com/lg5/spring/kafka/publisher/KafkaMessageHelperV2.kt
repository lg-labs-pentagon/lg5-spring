package com.lg5.spring.kafka.publisher


import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.lg5.spring.kafka.publisher.exception.KafkaMessageException
import com.lg5.spring.kafka.publisher.service.impl.KafkaProducerV2Impl
import com.lg5.spring.outbox.com.lg5.spring.outbox.OutboxStatus
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.support.SendResult
import java.util.function.BiConsumer


class KafkaMessageHelperV2(private val objectMapper: ObjectMapper) {

    fun <T, U> getKafkaCallback(
        topicName: String,
        avroModel: T,
        outboxMessage: U,
        outboxCallback: BiConsumer<U, OutboxStatus?>,
        eventId: String,
        avroModelName: String
    ): BiConsumer<SendResult<String, T>, Throwable> {
        return BiConsumer<SendResult<String, T>, Throwable> { result: SendResult<String, T>, ex: Throwable? ->
            if (ex == null) {
                val metadata = result.recordMetadata
                LOG.info(
                    "Received successful response from Kafka for event id: {} "
                            + " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                    eventId,
                    metadata.topic(),
                    metadata.partition(),
                    metadata.offset(),
                    metadata.timestamp()
                )
                outboxCallback.accept(outboxMessage, OutboxStatus.COMPLETED)
            } else {
                LOG.error(
                    "Error while sending {} with message: {} and outbox type: {} to topic {}",
                    avroModelName,
                    avroModel.toString(),
                    outboxMessage!!::class.java.name,
                    topicName, ex
                )
                outboxCallback.accept(outboxMessage, OutboxStatus.FAILED)
            }
        }
    }

    /**
     * Convert all event's payload from String to a class specified
     */
    fun <T> stringToObjectClass(payload: String, outputType: Class<T>): T {
        try {
            return objectMapper.readValue(payload, outputType)
        } catch (e: JsonProcessingException) {
            LOG.error("Could not read {} object!", outputType.name, e)
            throw KafkaMessageException("Could not read " + outputType.name + " object!", e)
        }
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(KafkaProducerV2Impl::class.java)
    }
}
