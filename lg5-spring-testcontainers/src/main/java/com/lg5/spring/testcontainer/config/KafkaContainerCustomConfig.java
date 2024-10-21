package com.lg5.spring.testcontainer.config;

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
    public static final int KAFKA_INTERNAL_PORT_AS_9093 = 9093;
    public static final int KAFKA_INTERNAL_PORT_AS_9092 = 9092;

    @Bean
    @Order(2)
    public KafkaContainer kafkaContainer(Environment environment) {
        KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse(CONFLUENTINC_CP_KAFKA_7_6_1))
                .withExposedPorts(KAFKA_INTERNAL_PORT_AS_9093)
                .withNetwork(network)
                .withNetworkAliases(KAFKA_NETWORK_ALIAS)
                .waitingFor(Wait.forListeningPort())
                .withReuse(dockerContainerReuse);
        kafkaContainer.start();

        String kafkaBootstrapServers = kafkaContainer.getBootstrapServers();
        withBootstrapServersCustom(kafkaContainer);

        if (environment instanceof StandardEnvironment) {
            MutablePropertySources propertySources = ((StandardEnvironment) environment).getPropertySources();
            Map<String, Object> map = new HashMap<>();
            map.put("kafka-config.bootstrap-servers", kafkaBootstrapServers);
            propertySources.addFirst(new MapPropertySource("kafkaProperties", map));
        }

        return kafkaContainer;
    }

    @Bean
    @Order(3)
    @DependsOn({"kafkaContainer"})
    public GenericContainer<?> schemaRegistryContainer(Environment environment) {
        GenericContainer<?> schemaRegistryContainer = new GenericContainer<>(DockerImageName.parse(CONFLUENTINC_CP_SCHEMA_REGISTRY_7_6_1))
                .withNetwork(network)
                .withNetworkAliases(SCHEMA_REGISTRY_NETWORK_ALIAS)
                .withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "PLAINTEXT://" + KAFKA_NETWORK_ALIAS + ":"+ KAFKA_INTERNAL_PORT_AS_9092)
                .withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
                .withExposedPorts(8081)
                .waitingFor(Wait.forListeningPort())
                .waitingFor(Wait.forHttp("/subjects").forStatusCode(200))
                .withReuse(dockerContainerReuse);
        schemaRegistryContainer.start();

        String schemaRegistryUrl = "http://" + schemaRegistryContainer.getHost() + ":" + schemaRegistryContainer.getMappedPort(8081);


        if (environment instanceof StandardEnvironment) {
            MutablePropertySources propertySources = ((StandardEnvironment) environment).getPropertySources();
            Map<String, Object> map = new HashMap<>();
            map.put("kafka-config.schema-registry-url", schemaRegistryUrl);
            propertySources.addFirst(new MapPropertySource("schemaRegistryProperties", map));
        }

        return schemaRegistryContainer;
    }

    private static void withBootstrapServersCustom(KafkaContainer kafkaContainer) {
        final String kafkaUrl = KAFKA_NETWORK_ALIAS + ":" + KAFKA_INTERNAL_PORT_AS_9092;
        kafkaContainer.withEnv(BOOTSTRAP_SERVERS_CUSTOM, kafkaUrl);
    }

}
