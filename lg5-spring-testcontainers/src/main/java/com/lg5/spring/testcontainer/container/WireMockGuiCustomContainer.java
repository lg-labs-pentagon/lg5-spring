package com.lg5.spring.testcontainer.container;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import static com.lg5.spring.testcontainer.util.Constant.WIREMOCK_GUI_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.util.Constant.WIREMOCK_GUI_PORT_DEFAULT;
import static com.lg5.spring.testcontainer.util.Constant.network;

/**
 * Images:
 * - WIREMOCK_GUI_V_3_6_31
 */
public class WireMockGuiCustomContainer extends GenericContainer<AppCustomContainer> {

    public WireMockGuiCustomContainer(String imageName) {
        this(DockerImageName.parse(imageName));
    }

    // Endpoint=> localhost:[dynamic_port]/__admin/webapp
    public WireMockGuiCustomContainer(DockerImageName dockerImageName) {
        super(dockerImageName);
        this.withExposedPorts(WIREMOCK_GUI_PORT_DEFAULT);
        this.withNetwork(network);
        this.withNetworkAliases(WIREMOCK_GUI_NETWORK_ALIAS);
        this.waitingFor(Wait.forListeningPort());
    }

}
