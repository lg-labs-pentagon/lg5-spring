group = "com.lg5.spring"
version = project.version
dependencies {
    implementation(libs.springboot.start.web)
    api(libs.slf4j.api)
    api(libs.logstash.logback.encoder) {
        exclude(group = "logback-core", module = "ch.qos.logback")
    }
    api(libs.janino)
    api(libs.springcloud.starter.sleuth)
    api(libs.java.uuid.generator)
    api(libs.snappy.java)
}

repositories {
    mavenCentral()
}
