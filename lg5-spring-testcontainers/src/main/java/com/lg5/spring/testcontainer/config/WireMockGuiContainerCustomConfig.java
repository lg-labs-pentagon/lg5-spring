package com.lg5.spring.testcontainer.config;

import com.lg5.spring.testcontainer.container.BaseContainerCustomConfig;
import com.lg5.spring.testcontainer.container.WireMockGuiCustomContainer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import static com.lg5.spring.testcontainer.util.Constant.WIREMOCK__GUI_V_3_6_31;

@TestConfiguration
public class WireMockGuiContainerCustomConfig extends BaseContainerCustomConfig {

    @Bean
    @Order(5)
    public WireMockGuiCustomContainer wireMockGuiCustomContainer() {
        final WireMockGuiCustomContainer wireMockGuiCustomContainer = new WireMockGuiCustomContainer(WIREMOCK__GUI_V_3_6_31);
        wireMockGuiCustomContainer.start();
        return wireMockGuiCustomContainer;
    }

}
