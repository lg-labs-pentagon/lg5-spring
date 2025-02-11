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
    val lg5SpringParentVersion: String by project
    dependencies {
        implementation(platform(libs.springboot.dependencies))
    }
}


extensions.configure<PublishingExtension> {
    publications {
        create<MavenPublication>("parentJava") {
            from(components["java"])

            pom {
                dependencyManagement {
                    dependencies {
                        // <!-- Starter Apps -->
                        dependency("com.lg5.spring:lg5-spring-starter:${project.version}")
                        //  <!--Common Modules-->
                        dependency("com.lg5.spring:lg5-spring-data-jpa:${project.version}")
                        dependency("com.lg5.spring:lg5-spring-api-rest:${project.version}")
                        dependency("com.lg5.spring:lg5-spring-logger:${project.version}")

                        //  <!--SAGA Pattern-->
                        dependency("com.lg5.jvm:lg5-jvm-saga:${project.version}")

                        // <!--OUTBOX Pattern-->
                        dependency("com.lg5.spring.outbox:lg5-spring-outbox:${project.version}")

                        //  <!-- Rest Client-->
                        dependency("com.lg5.spring:lg5-spring-client:${project.version}")

                        //  <!-- Kafka -->
                        dependency("com.lg5.spring.kafka:lg5-spring-kafka-producer:${project.version}")
                        dependency("com.lg5.spring.kafka:lg5-spring-kafka-consumer:${project.version}")
                        dependency("com.lg5.spring.kafka:lg5-spring-kafka-model:${project.version}")

                        //  <!-- Third utilities - Owner LgLabs-->
                        dependency("lg5.common:lg5-common-domain:${project.version}")
                        dependency("lg5.common:lg5-common-application-service:${project.version}")
                        dependency("com.lg5.jvm:lg5-jvm-utils:${project.version}")
                        dependency("com.lg5.spring:lg5-spring-utils:${project.version}")

                        // <!-- tests -->
                        dependency("com.lg5.jvm:lg5-jvm-unit-test:${project.version}")
                        dependency("com.lg5.spring:lg5-spring-integration-test:${project.version}")
                        dependency("com.lg5.spring:lg5-spring-test:${project.version}")
                        dependency("com.lg5.spring:lg5-spring-testcontainers:${project.version}") {
                            exclude("org.springframework.boot:spring-boot-starter-web")
                            exclude("org.springframework.boot:spring-boot-starter-test")
                        }
                        dependency("com.lg5.spring:lg5-spring-acceptance-test:${project.version}")
                    }

                }
            }

            pom.withXml {
                asNode()
                    .appendNode("parent")
                    .apply {
                        appendNode("groupId", libs.springboot.parent.get().group)
                        appendNode("artifactId", libs.springboot.parent.get().name)
                        appendNode("version", libs.versions.springboot.version.get())
                        appendNode("relativePath")
                    }
            }
            pom.packaging = "pom"

            pom.properties.put("lg5.version", project.version.toString())
            pom.properties.put("digest-amd", "sha256:d5f21fdaf076baf2d2ed134cdc82d8c344cb404b79cee54410a0aca10de986e1")
            pom.properties.put("digest-arm", "sha256:fa34005cdaa8b1f1f7190c3030bdf3a88b92ea27e61325b616ba9dfb9139f8b2")
            pom.properties.put("base-image", "gcr.io/distroless/java21-debian12")
            pom.properties.put("graph-include", "none")
            pom.properties.put("graph-exclude", "none")

            pom.withXml {
                asNode()
                    .appendNode("build").apply {
                        appendNode("pluginManagement")
                            .appendNode("plugins").apply {
                                avroPlugin()
                                jibMavenPlugin()
                                springBootMavenBuildImagePlugin()
                                surefirePlugin()
                                failsafePlugin()
                                jacocoPlugin()
                                mavenCheckstylePlugin()
                                mavenCompilerPlugin()
                                depgraphMavenPlugin()
                            }
                        appendNode("plugins").apply {
                            mavenCompilerPluginSimple()
                            surefirePluginSimple()
                            failsafePluginSimple()
                            jacocoPluginSimple()
                            mavenCheckstylePluginSimple()
                            depgraphMavenPluginSimple()
                        }
                    }
                asNode()
                    .profiles()
                asNode()
                    .repositories()
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
tasks.jar {
    enabled = true
    from("checkstyle.xml")
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
        appendNode("configuration").apply {
            appendNode("release", 21)

            appendNode("annotationProcessorPaths").apply {
                appendNode("path").apply {
                    appendNode("groupId", libs.mapstruct.get().group)
                    appendNode("artifactId", "mapstruct-processor")
                    appendNode("version", libs.mapstruct.get().version)
                }
                appendNode("path").apply {
                    appendNode("groupId", libs.lombok.get().group)
                    appendNode("artifactId", libs.lombok.get().name)
                    appendNode("version", libs.lombok.get().version)
                }
                appendNode("path").apply {
                    appendNode("groupId", libs.lombokmapstruct.binding.get().group)
                    appendNode("artifactId", "lombok-mapstruct-binding")
                    appendNode("version", libs.lombokmapstruct.binding.get().version)
                }
            }
        }

    }
}

fun Node.mavenCompilerPluginSimple() {

    appendNode("plugin").apply {
        appendNode("groupId", "org.apache.maven.plugins")
        appendNode("artifactId", "maven-compiler-plugin")
    }
}

fun Node.surefirePlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.surefire.plugin.get().group)
        appendNode("artifactId", libs.surefire.plugin.get().name)
        appendNode("version", libs.surefire.plugin.get().version)

        appendNode("configuration").apply {
            appendNode("argLine", "\${surefireArgLine}")

            appendNode("includes").apply {
                appendNode("include", "**/*Test.java")
            }

            appendNode("excludes").apply {
                appendNode("exclude", "**/*AcceptanceTestCase.java")
                appendNode("exclude", "**/*AcceptanceT.java")
                appendNode("exclude", "**/*AT.java")
                appendNode("exclude", "*-report/**")
                appendNode("exclude", "*-support/**")
                appendNode("exclude", "*-acceptance-test/**")
                appendNode("exclude", "*.xml")
            }
        }
    }
}

fun Node.surefirePluginSimple() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.surefire.plugin.get().group)
        appendNode("artifactId", libs.surefire.plugin.get().name)
    }
}

fun Node.failsafePlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.failsafe.plugin.get().group)
        appendNode("artifactId", libs.failsafe.plugin.get().name)
        appendNode("version", libs.failsafe.plugin.get().version)

        appendNode("executions").apply {

            appendNode("execution").apply {

                appendNode("goals").apply {
                    appendNode("goal", "verify")
                    appendNode("goal", "integration-test")
                }

                appendNode("configuration").apply {
                    appendNode("argLine", "\${failsafeArgLine}")

                    appendNode("includes").apply {
                        appendNode("include", "**/*IT.java")
                    }

                    appendNode("excludes").apply {
                        appendNode("exclude", "*-report/**")
                        appendNode("exclude", "*-support/**")
                        appendNode("exclude", "*-acceptance-test/**")
                        appendNode("exclude", "*.xml")
                    }
                }
            }


            appendNode("execution").apply {
                appendNode("id", "acceptance-test")
                appendNode("phase", "verify")

                appendNode("goals").apply {
                    appendNode("goal", "integration-test")
                    appendNode("goal", "verify")
                }

                appendNode("configuration").apply {
                    appendNode("argLine", "\${failsafeArgLine}")
                    appendNode("includes").apply {
                        appendNode("include", "**/*AcceptanceTestCase.java")
                        appendNode("include", "**/*AcceptanceT.java")
                        appendNode("include", "**/*AT.java")
                    }
                    appendNode("excludes").apply {
                        appendNode("exclude", "*-report/**")
                        appendNode("exclude", "*-support/**")
                        appendNode("exclude", "*-acceptance-test/**")
                        appendNode("exclude", "*.xml")
                    }
                }
            }
        }
    }
}

fun Node.failsafePluginSimple() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.failsafe.plugin.get().group)
        appendNode("artifactId", libs.failsafe.plugin.get().name)
    }
}

fun Node.jacocoPlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", "org.jacoco")
        appendNode("artifactId", "jacoco-maven-plugin")
        appendNode("version", libs.versions.jacoco.version.get())

        appendNode("executions").apply {

            appendNode("execution").apply {
                appendNode("id", "prepare-agent")
                    .children().apply {

                        appendNode("goals")
                            .appendNode("goal", "prepare-agent")
                        appendNode("configuration")
                            .appendNode("propertyName", "surefireArgLine")
                    }
            }
            appendNode("execution").apply {
                appendNode("id", "prepare-agent-integration")
                    .children().apply {

                        appendNode("goals")
                            .appendNode("goal", "prepare-agent-integration")

                        appendNode("configuration")
                            .appendNode("propertyName", "failsafeArgLine")
                    }
            }
        }
        appendNode("configuration")
            .appendNode("excludes").apply {
                appendNode("exclude", "**/AvroModel.")
                appendNode("exclude", "**/**.kafka.*")
                appendNode("exclude", "infrastructure/kafka/*")
                appendNode("exclude", "*-acceptance-test/**")
                appendNode("exclude", "*.xml")

            }
    }
}

fun Node.jacocoPluginSimple() {
    appendNode("plugin").apply {
        appendNode("groupId", "org.jacoco")
        appendNode("artifactId", "jacoco-maven-plugin")
    }
}

fun Node.avroPlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.avro.plugin.get().group)
        appendNode("artifactId", libs.avro.plugin.get().name)
        appendNode("version", libs.versions.avro.version.get())


        appendNode("configuration").apply {
            appendNode("stringType", "String")
            appendNode("enableDecimalLogicalType", "true")
        }

        appendNode("executions").apply {
            appendNode("execution").apply {

                appendNode("phase", "generate-resources")
                appendNode("goals")
                    .appendNode("goal", "schema")


                appendNode("configuration").apply {
                    appendNode("sourceDirectory", "src/main/resources/avro")
                    appendNode("outputDirectory", "src/main/java")
                    appendNode("testSourceDirectory", "src/test/resources/avro")
                    appendNode("testOutputDirectory", "src/test/java")

                }
            }
        }
    }
}

fun Node.mavenCheckstylePlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.checkstyle.plugin.get().group)
        appendNode("artifactId", libs.checkstyle.plugin.get().name)
        appendNode("version", libs.checkstyle.plugin.get().version)

        appendNode("dependencies")
            .appendNode("dependency").apply {
                appendNode("groupId", libs.puppycrawl.tools.get().group)
                appendNode("artifactId", libs.puppycrawl.tools.get().name)
                appendNode("version", libs.puppycrawl.tools.get().version)
            }

        appendNode("executions").apply {
            appendNode("execution").apply {

                appendNode("id", "validate")
                appendNode("phase", "validate")
                appendNode("goals")
                    .appendNode("goal", "check")


                appendNode("configuration").apply {
                    appendNode("configLocation", "./checkstyle.xml")
                    appendNode("consoleOutput", "true")
                    appendNode("failsOnError", "true")
                    appendNode("violationSeverity", "warning")
                }
            }
        }
    }
}

fun Node.mavenCheckstylePluginSimple() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.checkstyle.plugin.get().group)
        appendNode("artifactId", libs.checkstyle.plugin.get().name)
    }
}

fun Node.jibMavenPlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.jib.plugin.get().group)
        appendNode("artifactId", libs.jib.plugin.get().name)
        appendNode("version", libs.jib.plugin.get().version)

        appendNode("configuration")
            .appendNode("from").apply {
                appendNode("image", "\${base-image}@\${digest}")
                appendNode("platforms")
                    .appendNode("platform").apply {
                        appendNode("architecture", "\${docker.from.image.platform.architecture}")
                        appendNode("os", "\${docker.from.image.platform.os}")
                    }
            }

        appendNode("executions")
            .appendNode("execution").apply {

                appendNode("id", "build-image")
                appendNode("phase", "post-integration-test")
                appendNode("goals")
                    .appendNode("goal", "dockerBuild")


                appendNode("configuration").apply {
                    appendNode("to").appendNode(
                        "image",
                        "\${project.groupId}/\${project.parent.artifactId}:\${project.version}"
                    )
                    appendNode("container").apply {
                        appendNode("creationTime", "USE_CURRENT_TIMESTAMP")
                        appendNode("jvmFlags").apply {
                            appendNode("jvmFlag", "-Duser.timezone=UTC")
                            appendNode("jvmFlag", "-XX:+PrintFlagsFinal")
                            appendNode("jvmFlag", "-XX:MaxRAMPercentage=50")
                        }
                    }
                }
            }
    }
}

fun Node.springBootMavenBuildImagePlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.springboot.parent.get().group)
        appendNode("artifactId", "spring-boot-maven-plugin")

        appendNode("configuration").apply {
            appendNode("image")
                .appendNode("name", "\${project.groupId}/\${project.parent.artifactId}:\${project.version}")
            appendNode("createdDate", "now")
            appendNode("skip", "false")
        }

        appendNode("executions")
            .appendNode("execution").apply {
                appendNode("phase", "install")
                appendNode("goals")
                    .appendNode("goal", "build-image")
            }

    }
}

fun Node.depgraphMavenPlugin() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.depgraph.maven.get().group)
        appendNode("artifactId", libs.depgraph.maven.get().name)
        appendNode("version", libs.depgraph.maven.get().version)

        appendNode("configuration").apply {
            appendNode("showGroupIds", false)
            appendNode("createImage", true)
            appendNode("showVersions", false)
            appendNode("reduceEdges", false)

            appendNode("scopes", "compile")
            appendNode("includeParentProjects", true)
            appendNode("showDuplicates", true)
            appendNode("showConflicts", true)


            appendNode("includes", "com.blanksystem*:*,\${graph-include}")
            appendNode("excludes", "*:*report*,*:*acceptance*,*:*support*,\${graph-exclude}")

        }

        appendNode("executions").apply {


            appendNode("execution").apply {
                appendNode("phase", "verify")
                appendNode("goals")
                    .appendNode("goal", "aggregate")
            }

        }


    }
}
fun Node.depgraphMavenPluginSimple() {
    appendNode("plugin").apply {
        appendNode("groupId", libs.depgraph.maven.get().group)
        appendNode("artifactId", libs.depgraph.maven.get().name)
    }
}
fun Node.profiles() {

    appendNode("profiles").apply {
        appendNode("profile").apply {

            appendNode("id", "amd")
            appendNode("properties").apply {
                appendNode("docker.from.image.platform.architecture", "amd64")
                appendNode("docker.from.image.platform.os", "linux")
                appendNode("digest", "\${digest-amd}")
            }
            appendNode("activation").apply {
                appendNode("activeByDefault", true)
            }
        }

        appendNode("profile").apply {

            appendNode("id", "arch-aarch64")
            appendNode("properties").apply {
                appendNode("docker.from.image.platform.architecture", "arm64")
                appendNode("docker.from.image.platform.os", "linux")
                appendNode("digest", "\${digest-arm}")
            }
            appendNode("activation").apply {
                appendNode("os")
                    .appendNode("arch", "aarch64")
            }
        }


    }
}

fun Node.repositories() {
    appendNode("repositories").apply {
        appendNode("repository").apply {
            appendNode("id", "confluent")
            appendNode("url", "https://packages.confluent.io/maven/")
        }
    }

}
