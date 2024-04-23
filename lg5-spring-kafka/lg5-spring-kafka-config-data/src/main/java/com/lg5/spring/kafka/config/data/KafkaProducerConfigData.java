package com.lg5.spring.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@ConfigurationProperties(prefix = "kafka-producer-config")
public class KafkaProducerConfigData {
    private String keySerializerClass;
    private String valueSerializerClass;
    private String compressionType;
    private String acks;
    private Integer batchSize;
    private Integer batchSizeBoostFactor;
    private Integer lingerMs;
    private Integer requestTimeoutMs;
    private Integer retryCount;

    public KafkaProducerConfigData(String keySerializerClass, String valueSerializerClass, String compressionType,
                                   String acks, Integer batchSize, Integer batchSizeBoostFactor, Integer lingerMs,
                                   Integer requestTimeoutMs, Integer retryCount) {
        this.keySerializerClass = keySerializerClass;
        this.valueSerializerClass = valueSerializerClass;
        this.compressionType = compressionType;
        this.acks = acks;
        this.batchSize = batchSize;
        this.batchSizeBoostFactor = batchSizeBoostFactor;
        this.lingerMs = lingerMs;
        this.requestTimeoutMs = requestTimeoutMs;
        this.retryCount = retryCount;
    }

    public String getKeySerializerClass() {
        return keySerializerClass;
    }

    public void setKeySerializerClass(String keySerializerClass) {
        this.keySerializerClass = keySerializerClass;
    }

    public String getValueSerializerClass() {
        return valueSerializerClass;
    }

    public void setValueSerializerClass(String valueSerializerClass) {
        this.valueSerializerClass = valueSerializerClass;
    }

    public String getCompressionType() {
        return compressionType;
    }

    public void setCompressionType(String compressionType) {
        this.compressionType = compressionType;
    }

    public String getAcks() {
        return acks;
    }

    public void setAcks(String acks) {
        this.acks = acks;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }

    public Integer getBatchSizeBoostFactor() {
        return batchSizeBoostFactor;
    }

    public void setBatchSizeBoostFactor(Integer batchSizeBoostFactor) {
        this.batchSizeBoostFactor = batchSizeBoostFactor;
    }

    public Integer getLingerMs() {
        return lingerMs;
    }

    public void setLingerMs(Integer lingerMs) {
        this.lingerMs = lingerMs;
    }

    public Integer getRequestTimeoutMs() {
        return requestTimeoutMs;
    }

    public void setRequestTimeoutMs(Integer requestTimeoutMs) {
        this.requestTimeoutMs = requestTimeoutMs;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KafkaProducerConfigData that = (KafkaProducerConfigData) o;
        return Objects.equals(getKeySerializerClass(), that.getKeySerializerClass()) && Objects.equals(getValueSerializerClass(), that.getValueSerializerClass()) && Objects.equals(getCompressionType(), that.getCompressionType()) && Objects.equals(getAcks(), that.getAcks()) && Objects.equals(getBatchSize(), that.getBatchSize()) && Objects.equals(getBatchSizeBoostFactor(), that.getBatchSizeBoostFactor()) && Objects.equals(getLingerMs(), that.getLingerMs()) && Objects.equals(getRequestTimeoutMs(), that.getRequestTimeoutMs()) && Objects.equals(getRetryCount(), that.getRetryCount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeySerializerClass(), getValueSerializerClass(), getCompressionType(), getAcks(), getBatchSize(), getBatchSizeBoostFactor(), getLingerMs(), getRequestTimeoutMs(), getRetryCount());
    }
}
