plugins {
    kotlin("jvm") version "1.9.23"
}

dependencies {

    implementation(libs.springboot.starter)
    implementation(libs.lombok)
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}