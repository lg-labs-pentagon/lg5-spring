dependencies {
    api(libs.springboot.starter.test)
    implementation(libs.mockito.core)
    implementation(libs.jupiter.api)
    implementation(libs.jupiter.engine)
    testRuntimeOnly(libs.jupiter.launcher)
}
tasks.jar { enabled = true }