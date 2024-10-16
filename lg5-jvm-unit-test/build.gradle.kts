plugins {
    jacoco
}

group="com.lg5.jvm"
version = project.version

jacoco {
    toolVersion = libs.versions.jacoco.version.get()
}

dependencies {
    api(libs.jupiter.root)
    api(libs.mockito.core)
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run

    exclude("**/AvroModel.")
    exclude("**/**.kafka.*")
    exclude("infrastructure/kafka/*")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required = true
    }
}


tasks.jar { enabled = true }
