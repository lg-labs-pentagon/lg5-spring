package com.lg5.spring.testcontainer.config;

import com.lg5.spring.testcontainer.container.WireMockGuiCustomContainer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import static com.lg5.spring.testcontainer.util.Constant.WIREMOCK_GUI_V_3_8_46;

/**
 * Endpoint=> localhost:[dynamic_port]/__admin/webapp
 */
@TestConfiguration
@ConditionalOnProperty(name = "testcontainers.wiremockui.enabled", havingValue = "true", matchIfMissing = true)
public class WireMockGuiContainerCustomConfig extends BaseContainerCustomConfig {

    @Bean
    @Order(5)
    public WireMockGuiCustomContainer wireMockGuiCustomContainer() {
        final WireMockGuiCustomContainer wireMockGuiCustomContainer = new WireMockGuiCustomContainer(WIREMOCK_GUI_V_3_8_46);
        wireMockGuiCustomContainer.start();
        return wireMockGuiCustomContainer;
    }

}
