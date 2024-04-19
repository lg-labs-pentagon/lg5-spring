plugins {
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "1.2.1"
    checkstyle
    pmd
    kotlin("jvm")
    `maven-publish`
    `java`
}


group = "io.github.lg5.jvm.checkstyle.code"

gradlePlugin {
    website.set("https://github.com/lg-labs-pentagon/lg5-spring/lg5-jvm-checkstyle")
    vcsUrl.set("https://github.com/lg-labs-pentagon/lg5-spring/lg5-jvm-checkstyle")
    plugins {
        create("gradleCodeStylePluginExample") {
            id = "o.github.lg5.jvm.checkstyle.code"
            displayName = "Gradle Plugin Code Style Example"
            description = "Predefined Checkstyle and PMD rules"
            implementationClass = "io.github.lg5.jvm.checkstyle.code.CodingRulesGradlePlugin"
            tags.set(listOf("codestyle", "checkstyle", "pmd"))
        }
    }
}



dependencies {
    implementation(libs.puppycrawl.tools)
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(libs.jupiter.root)
    testRuntimeOnly(libs.jupiter.launcher)
}

tasks.test {
    useJUnitPlatform()
}



repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain(21)
}