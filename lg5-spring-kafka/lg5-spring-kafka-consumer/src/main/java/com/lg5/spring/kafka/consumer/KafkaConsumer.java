package com.lg5.spring.kafka.consumer;

import org.apache.avro.specific.SpecificRecordBase;

import java.util.List;

public interface KafkaConsumer<T extends SpecificRecordBase> {
    void receive(final List<T> messages, final List<String> keys, final List<Integer> partitions, final List<Long> offsets);
}
