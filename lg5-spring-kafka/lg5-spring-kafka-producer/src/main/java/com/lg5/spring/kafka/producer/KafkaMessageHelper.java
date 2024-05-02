package com.lg5.spring.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lg5.spring.kafka.producer.exception.KafkaMessageException;
import com.lg5.spring.outbox.OutboxStatus;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;


@Component
public class KafkaMessageHelper {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageHelper.class);

    private final ObjectMapper objectMapper;

    public KafkaMessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    private <T> BiConsumer<SendResult<String, T>, Throwable> getCallback(String topicName, T avroModel) {
        return (result, ex) -> {
            if (ex == null) {
                final RecordMetadata metadata = result.getRecordMetadata();
                LOG.info("""
                                Received new metadata.
                                Topic: {};
                                Partition {};
                                Offset {};
                                Timestamp {};
                                at time {};
                                """,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            } else {
                LOG.error("""
                                Error while sending {}:
                                 - with message: {}
                                 - to topic {}
                                """,
                        avroModel.getClass().getName(),
                        avroModel.toString(),
                        topicName, ex);
            }
        };
    }

    @Deprecated
    public <T, U> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(String topicName,
                                                                                T avroModel,
                                                                                U outboxMessage,
                                                                                BiConsumer<U, OutboxStatus>
                                                                                        outboxCallback,
                                                                                String eventId,
                                                                                String avroModelName) {
        return getKafkaCallback(topicName, avroModel, outboxMessage, outboxCallback, eventId);
    }

    public <T, U> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(String topicName,
                                                                                T avroModel,
                                                                                U outboxMessage,
                                                                                BiConsumer<U, OutboxStatus>
                                                                                        outboxCallback,
                                                                                String eventId) {

        return (result, ex) -> {
            if (ex == null) {
                final RecordMetadata metadata = result.getRecordMetadata();
                LOG.info("""
                                Received new metadata.
                                Topic: {};
                                Event Id: {}
                                Partition {};
                                Offset {};
                                Timestamp {};
                                at time {};
                                """,
                        eventId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());

                outboxCallback.accept(outboxMessage, OutboxStatus.COMPLETED);
            } else {
                LOG.error("""
                                Error while sending {}:
                                 - event Id: {}
                                 - with message: {}
                                 - outbox type: {}
                                 - to topic {}
                                """,
                        eventId,
                        avroModel.getClass().getName(),
                        outboxMessage.getClass().getName(),
                        avroModel.toString(),
                        topicName, ex);
                outboxCallback.accept(outboxMessage, OutboxStatus.FAILED);
            }
        };
    }

    public <T> T stringToObjectClass(String payload, Class<T> outputType) {
        try {
            return objectMapper.readValue(payload, outputType);
        } catch (JsonProcessingException e) {
            LOG.error("Could not read {} object!", outputType.getName(), e);
            throw new KafkaMessageException("Could not read " + outputType.getName() + " object!", e);
        }
    }
}
