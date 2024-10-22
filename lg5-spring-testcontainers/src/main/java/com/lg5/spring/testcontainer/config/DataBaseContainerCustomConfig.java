package com.lg5.spring.testcontainer.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static com.lg5.spring.testcontainer.util.Constant.POSTGRES_17_0;
import static com.lg5.spring.testcontainer.util.Constant.POSTGRES_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.util.Constant.network;

@TestConfiguration
@Deprecated
public abstract class DataBaseContainerCustomConfig extends BaseContainerCustomConfig {
    public static final String JDBC_URL_CUSTOM = "JDBC_URL_CUSTOM";

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse(POSTGRES_17_0))
                .withNetwork(network)
                .withNetworkAliases(POSTGRES_NETWORK_ALIAS)
                .withReuse(dockerContainerReuse);
        postgreSQLContainer.withUrlParam("binaryTransfer", "true");
        postgreSQLContainer.withUrlParam("reWriteBatchedInserts", "true");
        postgreSQLContainer.withUrlParam("stringtype", "unspecified");
        withJdbcUrlCustom(postgreSQLContainer);
        return postgreSQLContainer;
    }

    private static void withJdbcUrlCustom(PostgreSQLContainer<?> postgreSQLContainer) {
        final String postgresUrl = String.format("jdbc:postgresql://" + POSTGRES_NETWORK_ALIAS + ":%d/test", PostgreSQLContainer.POSTGRESQL_PORT);
        postgreSQLContainer.withEnv(JDBC_URL_CUSTOM, postgresUrl);
    }
}
