package com.lg5.spring.testcontainer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import static com.lg5.spring.testcontainer.Constant.CONFLUENTINC_CP_KAFKA_7_6_1;
import static com.lg5.spring.testcontainer.Constant.CONFLUENTINC_CP_SCHEMA_REGISTRY_7_6_1;
import static com.lg5.spring.testcontainer.Constant.WIREMOCK_3_3_1;
import static com.lg5.spring.testcontainer.Constant.network;


@TestConfiguration
public abstract class AbstractCustomContainerConfig extends AbstractContainerNotReusedTest {


    @Container
    public static WireMockContainer wiremockServer = new WireMockContainer(WIREMOCK_3_3_1)
            .withMappingFromResource("placeholder", "wiremock/placeholder/template.json")
            .withNetwork(network)
            .withNetworkAliases("wiremock");

    @Container
    static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse(CONFLUENTINC_CP_KAFKA_7_6_1))
            .withNetwork(network)
            .withNetworkAliases("kafka");

    @Container
    static GenericContainer schemaRegistryContainer = new GenericContainer<>(DockerImageName.parse(CONFLUENTINC_CP_SCHEMA_REGISTRY_7_6_1))
            .withNetwork(network)
            .withNetworkAliases("schema-registry")
            .withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "kafka:9092")
            .withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
            .withEnv("SCHEMA_REGISTRY_LISTENERS", "http://0.0.0.0:8081")
            .withExposedPorts(8081);


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        Startables.deepStart(wiremockServer, kafkaContainer, schemaRegistryContainer).join();

        String schemaRegistryUrl = "http://" + schemaRegistryContainer.getHost() + ":" + schemaRegistryContainer.getMappedPort(8081);
        registry.add("third.jsonplaceholder.url", wiremockServer::getBaseUrl);
        registry.add("kafka-config.bootstrap-servers", kafkaContainer::getBootstrapServers);
        registry.add("kafka-config.schema-registry-url", () -> schemaRegistryUrl);
    }


}
