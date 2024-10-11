package com.lg5.spring.testcontainer.container;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static com.lg5.spring.testcontainer.util.Constant.POSTGRES_16_3;
import static com.lg5.spring.testcontainer.util.Constant.POSTGRES_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.util.Constant.network;

@TestConfiguration
public abstract class DataBaseContainerCustomConfig extends BaseContainerCustomConfig {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_16_3))
                .withNetwork(network)
                .withNetworkAliases(POSTGRES_NETWORK_ALIAS)
                .withReuse(dockerContainerReuse);
        postgreSQLContainer.withUrlParam("binaryTransfer", "true");
        postgreSQLContainer.withUrlParam("reWriteBatchedInserts", "true");
        postgreSQLContainer.withUrlParam("stringtype", "unspecified");
        return postgreSQLContainer;
    }
}
