package com.lg5.spring.testcontainer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lg5.spring.testcontainer.Constant.WIREMOCK_3_3_1;
import static com.lg5.spring.testcontainer.Constant.WIREMOCK_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.Constant.network;


@TestConfiguration
public abstract class WiremockContainerCustomConfig extends BaseContainerCustomConfig {
    @Value("${wiremock.config.folder:wiremock/placeholder/template.json}")
    protected String wireMockConfigFolderResource;

    @Value("${wiremock.config.url:third.jsonplaceholder.url}")
    protected String wireMockConfigUrl;

    @Bean
    @Order(4)
    public WireMockContainer wireMockContainer(Environment environment) {
        WireMockContainer wireMockContainer = new WireMockContainer(WIREMOCK_3_3_1)
                .withExposedPorts(8080)
                .withMappingFromResource("placeholder", wireMockConfigFolderResource)
                .withNetwork(network)
                .withNetworkAliases(WIREMOCK_NETWORK_ALIAS)
                .withReuse(dockerContainerReuse);
        wireMockContainer.setPortBindings(List.of("7071:8080"));

        wireMockContainer.start();

        String wireMockContainerBaseUrl = wireMockContainer.getBaseUrl();

        if (environment instanceof StandardEnvironment) {
            MutablePropertySources propertySources = ((StandardEnvironment) environment).getPropertySources();
            Map<String, Object> map = new HashMap<>();
            map.put(wireMockConfigUrl, wireMockContainerBaseUrl);
            propertySources.addFirst(new MapPropertySource("wiremockProperties", map));
        }
        return wireMockContainer;
    }
}
