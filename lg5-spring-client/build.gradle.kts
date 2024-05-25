java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    api(libs.openfeign)
}

tasks.jar { enabled = true }