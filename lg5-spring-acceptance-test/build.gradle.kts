group = "com.lg5.spring"
version = project.version

dependencies{
    api(libs.jupiter.root)
    api(libs.junit.platform.suite)
    api(libs.cucumber.java)
    api(libs.cucumber.junit.platform.engine)
    api(libs.cucumber.spring)
}

repositories {
    mavenCentral()
}