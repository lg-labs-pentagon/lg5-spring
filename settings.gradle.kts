plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "lg5-spring"
include("lg5-spring-parent")
include("lg5-spring-test")
include("lg5-spring-utils")
include("lg5-jvm-test")
include("lg5-jvm-utils")
