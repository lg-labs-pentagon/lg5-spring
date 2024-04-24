plugins {
    kotlin("jvm")
}

group = "com.lg5.spring.outbox"
version = project.version

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