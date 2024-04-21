plugins {
    kotlin("jvm") version "1.9.23"
    java
    alias(libs.plugins.springboot.plugin) apply false
    alias(libs.plugins.spring.dependency.management)
}

java {
    withSourcesJar()
    true
    sourceCompatibility=JavaVersion.VERSION_21
}

dependencies {

    implementation(libs.springboot.starter)
    implementation(libs.lombok)
    implementation(kotlin("stdlib"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar { enabled = true }