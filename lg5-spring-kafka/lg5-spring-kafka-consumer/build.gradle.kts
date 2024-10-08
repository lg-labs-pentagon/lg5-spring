plugins {
    kotlin("jvm") version "1.9.23"
    alias(libs.plugins.springboot.plugin) apply false
    alias(libs.plugins.spring.dependency.management)
}

java {
    withSourcesJar()
    sourceCompatibility=JavaVersion.VERSION_21

}
sourceSets {

}
dependencies {
    implementation(libs.springboot.starter)
    api(libs.spring.kafka)
    api(libs.apache.avro)
    api(libs.postgresql)
    implementation(project(":lg5-spring-kafka:lg5-spring-kafka-config"))
    testImplementation(project(":lg5-spring-test"))

}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar { enabled = true }