plugins {
    id("java")
    alias(libs.plugins.springboot.plugin) apply false
    alias(libs.plugins.spring.dependency.management)
}

group = "com.lg5.spring.kafka.config.data"
version = "1.0.0-alpha"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springboot.starter)
    implementation(libs.lombok)
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar{enabled=true}