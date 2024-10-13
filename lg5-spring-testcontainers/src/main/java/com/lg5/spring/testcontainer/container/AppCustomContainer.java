package com.lg5.spring.testcontainer.container;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.Map;

import static com.lg5.spring.testcontainer.util.Constant.APP_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.util.Constant.APP_PORT_DEFAULT;
import static com.lg5.spring.testcontainer.util.Constant.network;

/**
 * TODO: adapt to app container for services without port
 */
public class AppCustomContainer extends GenericContainer<AppCustomContainer> {

    public static RequestSpecification requestSpecification;

    public AppCustomContainer(String imageName) {
        this(DockerImageName.parse(imageName));
    }

    public AppCustomContainer(DockerImageName dockerImageName) {
        super(dockerImageName);
        this.withExposedPorts(APP_PORT_DEFAULT);
        this.withNetwork(network);
        this.withNetworkAliases(APP_NETWORK_ALIAS);
        this.withEnv("SERVER_PORT", String.valueOf(APP_PORT_DEFAULT));
        this.waitingFor(Wait.forListeningPort());
        this.waitingFor(new HttpWaitStrategy()
                .forPath("/actuator/health")
                .forPort(APP_PORT_DEFAULT)
                .withStartupTimeout(Duration.ofSeconds(50)));
        this.withReuse(false);
    }

    public void withAppEnvVars(Map<String, String> appEnvVars) {
        this.withEnv(appEnvVars);
    }

    public void initRequestSpecification() {
        requestSpecification = new RequestSpecBuilder()
                .setPort(this.getFirstMappedPort())
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
