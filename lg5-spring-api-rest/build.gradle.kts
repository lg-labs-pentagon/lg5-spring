dependencies {
    api(libs.springboot.start.web)
    api(libs.springboot.start.actuator)
    api(libs.springboot.validation)
    implementation(libs.slf4j.api)
}
repositories {
    mavenCentral()
}