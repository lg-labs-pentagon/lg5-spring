plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "lg5-spring"
include("lg5-spring-parent")
include("lg5-spring-test")
include("lg5-spring-utils")
include("lg5-jvm-test")
include("lg5-jvm-utils")
include("lg5-spring-kafka")
include("lg5-spring-kafka:lg5-spring-kafka-config")
findProject(":lg5-spring-kafka:lg5-spring-kafka-config")?.name = "lg5-spring-kafka-config"
include("lg5-spring-kafka:lg5-spring-kafka-producer")
findProject(":lg5-spring-kafka:lg5-spring-kafka-producer")?.name = "lg5-spring-kafka-producer"
include("lg5-spring-kafka:lg5-spring-kafka-consumer")
findProject(":lg5-spring-kafka:lg5-spring-kafka-consumer")?.name = "lg5-spring-kafka-consumer"
include("lg5-spring-outbox")
include("lg5-jvm-saga")
include("lg5-spring-kafka:lg5-spring-kafka-model")
findProject(":lg5-spring-kafka:lg5-spring-kafka-model")?.name = "lg5-spring-kafka-model"
include("lg5-common")
include("lg5-common:lg5-common-application-service")
findProject(":lg5-common:lg5-common-application-service")?.name = "lg5-common-application-service"
include("lg5-common:lg5-common-domain")
findProject(":lg5-common:lg5-common-domain")?.name = "lg5-common-domain"
include("lg5-spring-api-rest")
include("lg5-spring-data-jpa")
include("lg5-spring-logger")
include("lg5-spring-starter")
include("lg5-spring-testcontainers")
include("lg5-spring-client")
