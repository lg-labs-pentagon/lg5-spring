plugins {
    kotlin("jvm")
}

group="com.lg5.jvm"
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