dependencies {
    testImplementation(libs.springboot.starter.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.jupiter.api)
    testImplementation(libs.jupiter.engine)
    testRuntimeOnly(libs.jupiter.launcher)
}
