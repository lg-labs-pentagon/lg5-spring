dependencies {

    api(libs.logstash.logback.encoder) {
        // <--TODO validate if it does not have change when active this comment .-- >
        exclude(group = "logback-core", module = "ch.qos.logback")
    }


    // <-- TODO validate  the versions-->
    api(libs.snappy.java)
    api(libs.springcloud.starter.sleuth)
    api(libs.java.uuid.generator)
}