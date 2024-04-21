subprojects{
    group = project.group.toString().plus(".kafka")
    version = project.version

    repositories {
        mavenCentral()
    }
}

