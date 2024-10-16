java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}
dependencies {
    implementation(libs.springboot.start.web)
    implementation(libs.springboot.start.aop)
    api(libs.springboot.testcontainers)
    api(libs.testcontainers.jupiter)
    api(libs.testcontainers.postgresql)
    api(libs.testcontainers.kafka)
    api(libs.testcontainers.wiremock)
    api(libs.wiremock.standalone)
    api(libs.rest.assured)
    api(project(":lg5-spring-test")){
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
}
tasks.jar { enabled = true }