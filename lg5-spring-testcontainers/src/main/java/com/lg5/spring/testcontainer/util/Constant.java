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
    public static final String WIREMOCK_GUI_NETWORK_ALIAS = "mock-gui";

    // ports
    public static final int APP_PORT_DEFAULT = 8080;
    public static final int WIREMOCK_GUI_PORT_DEFAULT = 8080;

    // images
    public static final String POSTGRES_17_0 = "postgres:17.2";
    public static final String CONFLUENTINC_CP_KAFKA_7_6_1 = "confluentinc/cp-kafka:7.6.1";
    public static final String CONFLUENTINC_CP_KAFKA_7_7_1 = "confluentinc/cp-kafka:7.7.1";
    public static final String CONFLUENTINC_CP_KAFKA_7_8_1 = "confluentinc/cp-kafka:7.8.1";
    public static final String CONFLUENTINC_CP_SCHEMA_REGISTRY_7_6_1 = "confluentinc/cp-schema-registry:7.6.1";
    public static final String CONFLUENTINC_CP_SCHEMA_REGISTRY_7_7_1 = "confluentinc/cp-schema-registry:7.7.1";
    public static final String CONFLUENTINC_CP_SCHEMA_REGISTRY_7_8_1 = "confluentinc/cp-schema-registry:7.8.1";
    @Deprecated
    public static final String WIREMOCK_3_3_1 = "wiremock/wiremock:3.3.1";
    public static final String WIREMOCK_3_9_1 = "wiremock/wiremock:3.9.1";
    public static final String WIREMOCK_3_11_0 = "wiremock/wiremock:3.11.0";
    @Deprecated
    public static final String WIREMOCK_GUI_V_3_6_31 = "holomekc/wiremock-gui:3.6.31";
    public static final String WIREMOCK_GUI_V_3_8_46 = "holomekc/wiremock-gui:3.8.46";
    public static final String WIREMOCK_GUI_V_3_10_9 = "holomekc/wiremock-gui:3.10.9";


    private Constant() {
    }
}
