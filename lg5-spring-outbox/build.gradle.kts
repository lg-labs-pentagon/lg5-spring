plugins {
    kotlin("jvm")
}

group = "com.lg5.spring.outbox"
version = project.version

java {
    withSourcesJar()
    sourceCompatibility=JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springboot.starter)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
tasks.jar { enabled = true }