package com.lg5.spring.testcontainer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static com.lg5.spring.testcontainer.Constant.POSTGRES_16_3;
import static com.lg5.spring.testcontainer.Constant.POSTGRES_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.Constant.network;

@TestConfiguration
public abstract class DataBaseContainerCustomConfig extends BaseContainerCustomConfig {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        return new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_16_3))
                .withNetwork(network)
                .withNetworkAliases(POSTGRES_NETWORK_ALIAS)
                .withReuse(dockerContainerReuse);
    }
}
