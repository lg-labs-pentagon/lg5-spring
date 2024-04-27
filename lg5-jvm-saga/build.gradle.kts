plugins {
    kotlin("jvm")
}

group = "com.lg5.spring.saga"
version = project.version

java {
    withSourcesJar()
    sourceCompatibility=JavaVersion.VERSION_21
}
repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(21)
}