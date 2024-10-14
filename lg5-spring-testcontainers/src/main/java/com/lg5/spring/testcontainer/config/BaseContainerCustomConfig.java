package com.lg5.spring.testcontainer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public abstract class BaseContainerCustomConfig {
    @Value("${docker.container.reuse:false}")
    protected boolean dockerContainerReuse;
}
