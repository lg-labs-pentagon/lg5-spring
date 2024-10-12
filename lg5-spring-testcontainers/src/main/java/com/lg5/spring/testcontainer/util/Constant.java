package com.lg5.spring.testcontainer.util;

import org.testcontainers.containers.Network;

public final class Constant {
    // networks
    public static final Network network = Network.newNetwork();
    public static final String KAFKA_NETWORK_ALIAS = "kafka";
    public static final String SCHEMA_REGISTRY_NETWORK_ALIAS = "schema-registry";
    public static final String POSTGRES_NETWORK_ALIAS = "postgres";
    public static final String WIREMOCK_NETWORK_ALIAS = "wiremock";
    public static final String APP_NETWORK_ALIAS = "app";

    // ports
    public static final int APP_PORT_DEFAULT = 8080;

    // images
    @Deprecated
    public static final String POSTGRES_16_3 = "postgres:16.3";
    public static final String POSTGRES_17_0 = "postgres:17.0";
    public static final String CONFLUENTINC_CP_KAFKA_7_6_1 = "confluentinc/cp-kafka:7.6.1";
    public static final String CONFLUENTINC_CP_SCHEMA_REGISTRY_7_6_1 = "confluentinc/cp-schema-registry:7.6.1";
    public static final String WIREMOCK_3_3_1 = "wiremock/wiremock:3.3.1";


    private Constant() {
    }
}
