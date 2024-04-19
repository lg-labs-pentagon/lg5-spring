plugins {
    jacoco
}
jacoco {
    toolVersion = "0.8.11"
}

dependencies {
    api(libs.jupiter.root)
    api(libs.mockito.core)
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required = true
    }
}


tasks.jar { enabled = true }
