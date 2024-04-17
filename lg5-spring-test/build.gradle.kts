dependencies {
    api(libs.springboot.starter.test)
    api(project(":lg5-jvm-test"))

}
tasks.jar { enabled = true }