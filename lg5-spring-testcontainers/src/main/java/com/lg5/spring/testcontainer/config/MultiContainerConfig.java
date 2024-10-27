package com.lg5.spring.testcontainer.config;

import org.testcontainers.containers.GenericContainer;

import java.util.Map;

public interface MultiContainerConfig extends ContainerConfig {

    Map<String, String> initializeEnvVariables(GenericContainer<?> container1, GenericContainer<?> container2);
}
