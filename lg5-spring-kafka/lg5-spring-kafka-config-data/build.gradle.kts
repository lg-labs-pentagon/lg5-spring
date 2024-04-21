plugins {
    id("java")
    alias(libs.plugins.springboot.plugin) apply false
    alias(libs.plugins.spring.dependency.management)
}
dependencies {
    implementation(libs.springboot.starter)
    implementation(libs.lombok)
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar{enabled=true}