plugins {
    kotlin("jvm") version "1.9.23"
    java
    `maven-publish`
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                pom.packaging = "pom"
            }
        }
        repositories {
            mavenLocal()
            maven {
                url = uri("https://maven.pkg.github.com/lg-labs-pentagon/lg5-spring")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }

}


allprojects {
    group = project.group
    version = project.version
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.register("distClean") {
    dependsOn("clean")
    doLast {
        delete("bin")
        delete (".classpath")
        delete (".gradle")
        delete (".nb-gradle")
        delete (".project")
        delete (".settings")
        delete (".vscode")
        delete (".DS_Store")
        delete (".idea")
    }
}