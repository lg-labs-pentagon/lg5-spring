package com.lg5.spring.testcontainer.aspect;

import com.lg5.spring.testcontainer.BaseContainerCustomConfig;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lg5.spring.testcontainer.Constant.WIREMOCK_3_3_1;
import static com.lg5.spring.testcontainer.Constant.WIREMOCK_NETWORK_ALIAS;
import static com.lg5.spring.testcontainer.Constant.network;

@Aspect
@Component
public class WireMockContainerAspect extends BaseContainerCustomConfig {

    private final Environment environment;

    @Value("${wiremock.config.folder:wiremock/placeholder/template.json}")
    protected String wireMockConfigFolderResource;

    @Value("${wiremock.config.url:third.jsonplaceholder.url}")
    protected String wireMockConfigUrl;

    @Value("${wiremock.config.port:7070}")
    protected String wireMockPortBind;


    public WireMockContainerAspect(Environment environment) {
        this.environment = environment;
    }

    @Before("@within(WireMockContainerCustom)")
    public void setUpWireMockContainerCustom() {
        wireMockContainer(environment);
    }

    private WireMockContainer wireMockContainer(Environment environment) {

        final WireMockContainer wireMockContainer = new WireMockContainer(WIREMOCK_3_3_1)
                .withExposedPorts(8080)
                .withMappingFromResource("placeholder", wireMockConfigFolderResource)
                .withNetwork(network)
                .withNetworkAliases(WIREMOCK_NETWORK_ALIAS)
                .withReuse(dockerContainerReuse);
        wireMockContainer.setPortBindings(List.of(wireMockPortBind + ":8080"));

        wireMockContainer.start();

        final String wireMockContainerBaseUrl = wireMockContainer.getBaseUrl();

        if (environment instanceof StandardEnvironment) {
            final MutablePropertySources propertySources = ((StandardEnvironment) environment).getPropertySources();
            final Map<String, Object> map = new HashMap<>();
            map.put(wireMockConfigUrl, wireMockContainerBaseUrl);
            propertySources.addFirst(new MapPropertySource("wiremockProperties", map));
        }
        return wireMockContainer;
    }
}

