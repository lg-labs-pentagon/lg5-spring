plugins{

    id("org.springframework.boot") version "3.2.5"

}


dependencies {
    api(libs.springboot.starter.parent)
    api(libs.springboot.logging)
    api(libs.guava)
    api(libs.lombok)
}
tasks.jar { enabled = true }