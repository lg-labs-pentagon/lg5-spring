group = "com.lg5.spring"
version = project.version

dependencies{
    api(libs.jupiter.root)
    api(libs.junit.platform.suite)
    api(libs.cucumber.java)
    api(libs.cucumber.junit.platform.engine)
    api(libs.cucumber.spring)
    implementation(project(":lg5-jvm-utils"))
    implementation(project(":lg5-spring-integration-test"))
}

repositories {
    mavenCentral()
}