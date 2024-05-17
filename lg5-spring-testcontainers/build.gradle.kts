java {
    withSourcesJar()
    sourceCompatibility=JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}
dependencies{
    implementation(libs.springboot.starter.test){
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }
    api(libs.springboot.testcontainers)
    api(libs.testcontainers.jupiter)
    api(libs.testcontainers.postgresql)
    api(libs.testcontainers.kafka)
}
tasks.jar { enabled = true }