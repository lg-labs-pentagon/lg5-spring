plugins {
    id("java")
    alias(libs.plugins.springboot.plugin) apply false
    alias(libs.plugins.spring.dependency.management)
}
version = project.version
dependencies {
    implementation(libs.springboot.starter)
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar{enabled=true}