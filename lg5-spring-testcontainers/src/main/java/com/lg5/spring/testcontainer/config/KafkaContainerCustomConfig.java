package com.lg5.spring.testcontainer.config;

import com.lg5.spring.testcontainer.util.BaseContainerCustomConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

import static com.lg5.spring.testcontainer.util.Constant.CONFLUENTINC_CP_KAFKA_7_6_1;
import static com.lg5.spring.testcontainer.util.Constant.CONFLUENTINC_CP_SCHEMA_REGISTRY_7_6_1;
import static com.lg5.spring.testcontainer.util.Constant.KAFKA_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.util.Constant.SCHEMA_REGISTRY_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.util.Constant.network;

@TestConfiguration
public abstract class KafkaContainerCustomConfig extends BaseContainerCustomConfig {
    public static final String BOOTSTRAP_SERVERS_CUSTOM = "BOOTSTRAP_SERVERS_CUSTOM";
    public static final int KAFKA_INTERNAL_PORT = 9092;

    @Bean
    @Order(2)
    public KafkaContainer kafkaContainer(Environment environment) {
        final KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse(CONFLUENTINC_CP_KAFKA_7_6_1))
                .withExposedPorts(9093)
                .withNetwork(network)
                .withNetworkAliases(KAFKA_NETWORK_ALIAS)
                .waitingFor(Wait.forListeningPort())
                .withReuse(dockerContainerReuse);
        kafkaContainer.start();

        withBootstrapServersCustom(kafkaContainer);
        setPropertiesWithKafka(environment, kafkaContainer);

        return kafkaContainer;
    }

    @Bean
    @Order(3)
    @DependsOn({"kafkaContainer"})
    public GenericContainer<?> schemaRegistryContainer(Environment environment) {
        try (GenericContainer<?> schemaRegistryContainer = new GenericContainer<>(DockerImageName.parse(CONFLUENTINC_CP_SCHEMA_REGISTRY_7_6_1))
                .withNetwork(network)
                .withNetworkAliases(SCHEMA_REGISTRY_NETWORK_ALIAS)
                .withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "PLAINTEXT://" + KAFKA_NETWORK_ALIAS + ":9092")
                .withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
                .withExposedPorts(8081)
                .waitingFor(Wait.forListeningPort())
                .waitingFor(Wait.forHttp("/subjects").forStatusCode(200))
                .withReuse(dockerContainerReuse)) {
            schemaRegistryContainer.start();

            setPropertiesWithSchemaRegistry(environment, schemaRegistryContainer);

            return schemaRegistryContainer;
        }
    }

    private static void setPropertiesWithSchemaRegistry(Environment environment, GenericContainer<?> schemaRegistryContainer) {
        final String schemaRegistryUrl = "http://" + schemaRegistryContainer.getHost() + ":"
                + schemaRegistryContainer.getMappedPort(8081);
        setProperties(environment, "kafka-config.schema-registry-url", schemaRegistryUrl, "schemaRegistryProperties");
    }

    private static void setPropertiesWithKafka(Environment environment, KafkaContainer kafkaContainer) {
        final String kafkaBootstrapServers = kafkaContainer.getBootstrapServers();
        setProperties(environment, "kafka-config.bootstrap-servers", kafkaBootstrapServers, "kafkaProperties");
    }

    private static void setProperties(Environment environment, String key, String kafkaBootstrapServers, String kafkaProperties) {
        if (environment instanceof StandardEnvironment env) {
            final MutablePropertySources propertySources = env.getPropertySources();
            final Map<String, Object> map = new HashMap<>();
            map.put(key, kafkaBootstrapServers);
            propertySources.addFirst(new MapPropertySource(kafkaProperties, map));
        }
    }

    private static void withBootstrapServersCustom(KafkaContainer kafkaContainer) {
        final String kafkaUrl = KAFKA_NETWORK_ALIAS + ":" + KAFKA_INTERNAL_PORT;
        kafkaContainer.withEnv(BOOTSTRAP_SERVERS_CUSTOM, kafkaUrl);
    }

}
