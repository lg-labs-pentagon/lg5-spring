dependencies {
    api(libs.springboot.starter.test){
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        exclude(group = "com.vaadin.external.google", module = "android-json")
    }

}
tasks.jar { enabled = true }