package com.lg5.spring.kafka.consumer

import org.apache.avro.specific.SpecificRecordBase


interface KafkaConsumer {
    fun receive(
        messages: List<SpecificRecordBase>,
        keys: List<String?>?,
        partitions: List<Int?>?,
        offsets: List<Long?>?
    )
}