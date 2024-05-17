package com.lg5.spring.testcontainer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static com.lg5.spring.testcontainer.Constant.CONFLUENTINC_CP_KAFKA_7_6_1;
import static com.lg5.spring.testcontainer.Constant.POSTGRES_16_3;

@TestConfiguration
public class TestContainerNotReusedConfig {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(
                DockerImageName.parse(POSTGRES_16_3)
        );
    }

    @Bean
    @ServiceConnection
    KafkaContainer kafkaContainer() {
        return new KafkaContainer(
                DockerImageName.parse(CONFLUENTINC_CP_KAFKA_7_6_1)
        );
    }
}
