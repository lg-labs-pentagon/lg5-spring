package com.lg5.spring.kafka.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lg5.spring.kafka.publisher.exception.KafkaMessageException;
import com.lg5.spring.outbox.com.lg5.spring.outbox.OutboxStatus;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;


@Component
public class KafkaMessageHelper {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageHelperV2.class);

    private final ObjectMapper objectMapper;

    public KafkaMessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public <T> T stringToObjectClass(String payload, Class<T> outputType) {
        try {
            return objectMapper.readValue(payload, outputType);
        } catch (JsonProcessingException e) {
            LOG.error("Could not read {} object!", outputType.getName(), e);
            throw new KafkaMessageException("Could not read " + outputType.getName() + " object!", e);
        }
    }

    public <T, U> BiConsumer<SendResult<String, T>, Throwable> getKafkaCallback(String topicName,
                                                                                T avroModel,
                                                                                U outboxMessage,
                                                                                BiConsumer<U, OutboxStatus>
                                                                                        outboxCallback,
                                                                                String eventId,
                                                                                String avroModelName) {

        return (result, ex) -> {
            if (ex == null) {
                final RecordMetadata metadata = result.getRecordMetadata();
                LOG.info("Received successful response from Kafka for event id: {} "
                                + " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        eventId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp()
                );
                outboxCallback.accept(outboxMessage, OutboxStatus.COMPLETED);
            } else {
                LOG.error("Error while sending {} with message: {} and outbox type: {} to topic {}",
                        avroModelName,
                        avroModel.toString(),
                        outboxMessage.getClass().getName(),
                        topicName, ex);
                outboxCallback.accept(outboxMessage, OutboxStatus.FAILED);
            }
        };
    }
}
