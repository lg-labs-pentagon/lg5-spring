import groovy.util.Node
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java apply true
    alias(libs.plugins.springboot.plugin) apply false
    alias(libs.plugins.spring.dependency.management)
    kotlin("jvm")
}


plugins.withType<JavaPlugin> {
    extensions.configure<JavaPluginExtension> {
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


            pom.withXml {
                asNode()
                    .appendNode("parent")
                    .apply {
                        appendNode("groupId", libs.springboot.starter.parent.get().group)
                        appendNode("artifactId", libs.springboot.starter.parent.get().name)
                        appendNode("version", libs.versions.springboot.version.get())
                    }
            }
            pom.packaging = "pom"
            pom.properties.put("lg5.version", "\${project.parent.version}")
            pom.withXml {
                asNode().appendNode("build").appendNode("plugins").apply {
                    mavenCompilerPlugin()
                    jacocoPlugin()
                }
            }
        }
    }
    tasks.withType<PublishToMavenLocal> {
        onlyIf {
            publication == publishing.publications["parentJava"]
        }
    }
    tasks.withType<PublishToMavenRepository> {
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
        jvmTarget = "21"
    }
}
tasks.jar { enabled = true }
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain(21)
}

fun Node.mavenCompilerPlugin() {

    appendNode("plugin").apply {
        appendNode("groupId", "org.apache.maven.plugins")
        appendNode("artifactId", "maven-compiler-plugin")
        appendNode("version", libs.versions.maven.compiler.plugin.version.get())
        appendNode("configuration")
            .appendNode("release", 21)

    }
}

fun Node.jacocoPlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", "org.jacoco")
        appendNode("artifactId", "jacoco-maven-plugin")
        appendNode("version", libs.versions.jacoco.version.get())

        appendNode("executions").apply {

            appendNode("execution").apply {
                appendNode("id", "prepare-agent").children().apply {
                    appendNode("goals")
                        .appendNode("goal", "prepare-agent")
                }
            }
        }
//        appendNode("configuration")
//            .appendNode("excludes").apply {
//                appendNode("exclude", "**/AvroModel.")
//                appendNode("exclude", "**/**.kafka.*")
//                appendNode("exclude", "infrastructure/kafka/*")
//            }
    }
}