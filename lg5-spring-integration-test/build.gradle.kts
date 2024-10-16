java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}
dependencies {
    implementation(libs.springboot.start.web)
    api(libs.springboot.starter.test){
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "com.vaadin.external.google", module = "android-json")
        exclude(group = "org.springframework.boot", module = "spring-boot-starter")
    }
    api(libs.awaitility)
    api(libs.wiremock.standalone)
    api(libs.rest.assured)
    implementation(project(":lg5-spring-testcontainers")){
        exclude(group = "io.rest-assured", module = "rest-assured")
        exclude(group = "org.wiremock", module = "wiremock-standalone")
        exclude(group = "org.springframework.boot", module = "spring-boot-test")
    }
}
tasks.jar { enabled = true }