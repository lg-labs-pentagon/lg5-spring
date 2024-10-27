package com.lg5.spring.testcontainer.config;

import org.testcontainers.containers.GenericContainer;

import java.util.Map;

public interface ContainerConfig {
    Map<String, String> initializeEnvVariables(GenericContainer<?> container);
}
