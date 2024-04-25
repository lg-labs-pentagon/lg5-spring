package com.lg5.spring.kafka.consumer

import org.apache.avro.specific.SpecificRecordBase


interface KafkaConsumerV2<T : SpecificRecordBase> {
    fun receive(messages: List<T>, keys: List<String>, partitions: List<Int>, offsets: List<Long>)
}