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
    main {
        java.srcDirs("src/main/java", "src/main/kotlin")
    }
}
dependencies {
    implementation(libs.springboot.starter)
    testImplementation(project(":lg5-spring-test"))

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.jar { enabled = true }