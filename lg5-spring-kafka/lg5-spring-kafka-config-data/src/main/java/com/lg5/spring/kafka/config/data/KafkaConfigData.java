package com.lg5.spring.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;


@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    private String bootstrapServers;
    private String schemaRegistryUrlKey;
    private String schemaRegistryUrl;
    private Integer numOfPartitions;
    private Short replicationFactor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KafkaConfigData that = (KafkaConfigData) o;
        return Objects.equals(bootstrapServers, that.bootstrapServers) && Objects.equals(schemaRegistryUrlKey, that.schemaRegistryUrlKey) && Objects.equals(schemaRegistryUrl, that.schemaRegistryUrl) && Objects.equals(numOfPartitions, that.numOfPartitions) && Objects.equals(replicationFactor, that.replicationFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bootstrapServers, schemaRegistryUrlKey, schemaRegistryUrl, numOfPartitions, replicationFactor);
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getSchemaRegistryUrlKey() {
        return schemaRegistryUrlKey;
    }

    public void setSchemaRegistryUrlKey(String schemaRegistryUrlKey) {
        this.schemaRegistryUrlKey = schemaRegistryUrlKey;
    }

    public String getSchemaRegistryUrl() {
        return schemaRegistryUrl;
    }

    public void setSchemaRegistryUrl(String schemaRegistryUrl) {
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    public Integer getNumOfPartitions() {
        return numOfPartitions;
    }

    public void setNumOfPartitions(Integer numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}
