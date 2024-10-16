java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
}

group="com.lg5.jvm"
version = project.version

repositories {
    mavenCentral()
}

dependencies {
    api(project(":lg5-jvm-test"))
}

tasks.jar { enabled = true }