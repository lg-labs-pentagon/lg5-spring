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
include("lg5-spring-kafka:lg5-spring-kafka-publisher")
findProject(":lg5-spring-kafka:lg5-spring-kafka-publisher")?.name = "lg5-spring-kafka-publisher"
include("lg5-spring-kafka:lg5-spring-kafka-consumer")
findProject(":lg5-spring-kafka:lg5-spring-kafka-consumer")?.name = "lg5-spring-kafka-consumer"
include("lg5-spring-outbox")
include("lg5-jvm-saga")
