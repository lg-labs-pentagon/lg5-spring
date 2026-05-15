plugins {
    kotlin("jvm") version "2.2.0"
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
    implementation(libs.springboot.logging)
    api(libs.kafka.avro.serializer){
        exclude(group = "org.slf4j", module = "slf4j-log4j12")
        exclude(group = "log4j", module = "log4j")
        exclude(group = "io.swagger", module = "swagger-annotation")
        exclude(group = "io.swagger", module = "swagger-core")
    }

    implementation(libs.springboot.json)
    implementation(project(":lg5-spring-kafka:lg5-spring-kafka-config"))
    api(project(":lg5-spring-outbox"))

}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
repositories{
    maven{
        name="confluent"
        url=uri("https://packages.confluent.io/maven/")
    }
}
tasks.jar { enabled = true }
