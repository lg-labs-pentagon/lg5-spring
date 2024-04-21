plugins {
    kotlin("jvm") version "1.9.23"
    java
    alias(libs.plugins.springboot.plugin) apply false
    alias(libs.plugins.spring.dependency.management)
}

java {
    withSourcesJar()
}

dependencies {

    implementation(libs.springboot.starter)
    implementation(libs.lombok)

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar { enabled = true }