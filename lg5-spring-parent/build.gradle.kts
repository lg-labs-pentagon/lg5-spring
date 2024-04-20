import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java apply true
    alias(libs.plugins.springboot.plugin) apply false
    alias(libs.plugins.spring.dependency.management)
}

version = "123231.1236"


plugins.withType<JavaPlugin> {
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    val springBootVersion: String by project
    dependencies {
        implementation(platform(libs.springboot.dependencies))
        implementation(libs.springboot.logging)
    }
}

extensions.configure<PublishingExtension> {
    publications {
        create<MavenPublication>("parentJava") {
            from(components["java"])
            pom.packaging = "pom"
            pom.withXml {
                asNode().appendNode("parent").apply {
                    appendNode("groupId", "org.springframework.boot")
                    appendNode("artifactId", "spring-boot-starter-parent")
                    appendNode("version", "3.2.3")
                }
            }
        }
    }
    tasks.withType<PublishToMavenLocal>{
        onlyIf {
            publication == publishing.publications["parentJava"]
        }

    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xjsr305=strict"
        )
        jvmTarget = "17"
    }
}

tasks.jar { enabled = true }