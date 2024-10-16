java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}
dependencies {
    api(libs.springboot.testcontainers)
    api(libs.testcontainers.jupiter)
    api(libs.testcontainers.postgresql)
    api(libs.testcontainers.kafka)
    api(libs.testcontainers.wiremock)
    api(libs.wiremock.standalone)
    api(libs.rest.assured)
    api(libs.springboot.test)
}
tasks.jar { enabled = true }