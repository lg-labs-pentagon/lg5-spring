package com.lg5.spring.testcontainer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static com.lg5.spring.testcontainer.Constant.CONFLUENTINC_CP_KAFKA_7_6_1;
import static com.lg5.spring.testcontainer.Constant.POSTGRES_16_3;

@Deprecated(forRemoval = true, since = "01-08-2024")
@TestConfiguration
public abstract class TestContainerReusedConfig {

    private static final boolean REUSABLE = true;

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(
                DockerImageName.parse(POSTGRES_16_3)
        ).withReuse(REUSABLE);// TRUE to no stop container, when finish test
    }

    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer() {
        return new KafkaContainer(
                DockerImageName.parse(CONFLUENTINC_CP_KAFKA_7_6_1)
        ).withReuse(REUSABLE);// TRUE to no stop container, when finish test
    }
}
