dependencies {
    implementation(libs.springboot.start.web)
    api(libs.slf4j.api)
    api(libs.logstash.logback.encoder) {
        exclude(group = "logback-core", module = "ch.qos.logback")
    }
    api(libs.janino)
    api(libs.micrometer.tracing)
    api(libs.java.uuid.generator)
    api(libs.snappy.java)
}

repositories {
    mavenCentral()
}
