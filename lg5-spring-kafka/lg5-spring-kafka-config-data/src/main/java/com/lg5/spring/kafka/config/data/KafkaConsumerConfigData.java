package com.lg5.spring.kafka.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {
    private String keyDeserializer;
    private String valueDeserializer;
    private String autoOffsetReset;
    private String specificAvroReaderKey;
    private String specificAvroReader;
    private Boolean batchListener;
    private Boolean autoStartup;
    private Integer concurrencyLevel;
    private Integer sessionTimeoutMs;
    private Integer heartbeatIntervalMs;
    private Integer maxPollIntervalMs;
    private Long pollTimeoutMs;
    private Integer maxPollRecords;
    private Integer maxPartitionFetchBytesDefault;
    private Integer maxPartitionFetchBytesBoostFactor;

    public KafkaConsumerConfigData(String keyDeserializer, String valueDeserializer, String autoOffsetReset, String specificAvroReaderKey, String specificAvroReader, Boolean batchListener, Boolean autoStartup, Integer concurrencyLevel, Integer sessionTimeoutMs, Integer heartbeatIntervalMs, Integer maxPollIntervalMs, Long pollTimeoutMs, Integer maxPollRecords, Integer maxPartitionFetchBytesDefault, Integer maxPartitionFetchBytesBoostFactor) {
        this.keyDeserializer = keyDeserializer;
        this.valueDeserializer = valueDeserializer;
        this.autoOffsetReset = autoOffsetReset;
        this.specificAvroReaderKey = specificAvroReaderKey;
        this.specificAvroReader = specificAvroReader;
        this.batchListener = batchListener;
        this.autoStartup = autoStartup;
        this.concurrencyLevel = concurrencyLevel;
        this.sessionTimeoutMs = sessionTimeoutMs;
        this.heartbeatIntervalMs = heartbeatIntervalMs;
        this.maxPollIntervalMs = maxPollIntervalMs;
        this.pollTimeoutMs = pollTimeoutMs;
        this.maxPollRecords = maxPollRecords;
        this.maxPartitionFetchBytesDefault = maxPartitionFetchBytesDefault;
        this.maxPartitionFetchBytesBoostFactor = maxPartitionFetchBytesBoostFactor;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public void setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
    }

    public String getSpecificAvroReaderKey() {
        return specificAvroReaderKey;
    }

    public void setSpecificAvroReaderKey(String specificAvroReaderKey) {
        this.specificAvroReaderKey = specificAvroReaderKey;
    }

    public String getSpecificAvroReader() {
        return specificAvroReader;
    }

    public void setSpecificAvroReader(String specificAvroReader) {
        this.specificAvroReader = specificAvroReader;
    }

    public Boolean getBatchListener() {
        return batchListener;
    }

    public void setBatchListener(Boolean batchListener) {
        this.batchListener = batchListener;
    }

    public Boolean getAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(Boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

    public Integer getConcurrencyLevel() {
        return concurrencyLevel;
    }

    public void setConcurrencyLevel(Integer concurrencyLevel) {
        this.concurrencyLevel = concurrencyLevel;
    }

    public Integer getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(Integer sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public Integer getHeartbeatIntervalMs() {
        return heartbeatIntervalMs;
    }

    public void setHeartbeatIntervalMs(Integer heartbeatIntervalMs) {
        this.heartbeatIntervalMs = heartbeatIntervalMs;
    }

    public Integer getMaxPollIntervalMs() {
        return maxPollIntervalMs;
    }

    public void setMaxPollIntervalMs(Integer maxPollIntervalMs) {
        this.maxPollIntervalMs = maxPollIntervalMs;
    }

    public Long getPollTimeoutMs() {
        return pollTimeoutMs;
    }

    public void setPollTimeoutMs(Long pollTimeoutMs) {
        this.pollTimeoutMs = pollTimeoutMs;
    }

    public Integer getMaxPollRecords() {
        return maxPollRecords;
    }

    public void setMaxPollRecords(Integer maxPollRecords) {
        this.maxPollRecords = maxPollRecords;
    }

    public Integer getMaxPartitionFetchBytesDefault() {
        return maxPartitionFetchBytesDefault;
    }

    public void setMaxPartitionFetchBytesDefault(Integer maxPartitionFetchBytesDefault) {
        this.maxPartitionFetchBytesDefault = maxPartitionFetchBytesDefault;
    }

    public Integer getMaxPartitionFetchBytesBoostFactor() {
        return maxPartitionFetchBytesBoostFactor;
    }

    public void setMaxPartitionFetchBytesBoostFactor(Integer maxPartitionFetchBytesBoostFactor) {
        this.maxPartitionFetchBytesBoostFactor = maxPartitionFetchBytesBoostFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KafkaConsumerConfigData that = (KafkaConsumerConfigData) o;
        return Objects.equals(getKeyDeserializer(), that.getKeyDeserializer()) && Objects.equals(getValueDeserializer(), that.getValueDeserializer()) && Objects.equals(getAutoOffsetReset(), that.getAutoOffsetReset()) && Objects.equals(getSpecificAvroReaderKey(), that.getSpecificAvroReaderKey()) && Objects.equals(getSpecificAvroReader(), that.getSpecificAvroReader()) && Objects.equals(getBatchListener(), that.getBatchListener()) && Objects.equals(getAutoStartup(), that.getAutoStartup()) && Objects.equals(getConcurrencyLevel(), that.getConcurrencyLevel()) && Objects.equals(getSessionTimeoutMs(), that.getSessionTimeoutMs()) && Objects.equals(getHeartbeatIntervalMs(), that.getHeartbeatIntervalMs()) && Objects.equals(getMaxPollIntervalMs(), that.getMaxPollIntervalMs()) && Objects.equals(getPollTimeoutMs(), that.getPollTimeoutMs()) && Objects.equals(getMaxPollRecords(), that.getMaxPollRecords()) && Objects.equals(getMaxPartitionFetchBytesDefault(), that.getMaxPartitionFetchBytesDefault()) && Objects.equals(getMaxPartitionFetchBytesBoostFactor(), that.getMaxPartitionFetchBytesBoostFactor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyDeserializer(), getValueDeserializer(), getAutoOffsetReset(), getSpecificAvroReaderKey(), getSpecificAvroReader(), getBatchListener(), getAutoStartup(), getConcurrencyLevel(), getSessionTimeoutMs(), getHeartbeatIntervalMs(), getMaxPollIntervalMs(), getPollTimeoutMs(), getMaxPollRecords(), getMaxPartitionFetchBytesDefault(), getMaxPartitionFetchBytesBoostFactor());
    }
}
