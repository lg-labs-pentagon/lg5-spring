package io.github.lg5.jvm.checkstyle.code

import java.lang.String.format
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.stream.Collectors.toSet
import java.util.stream.Stream

internal class CodingRulesGradlePluginTest {
    @Test
    fun shouldApplyPluginSuccessfully() {
        val project: Project = ProjectBuilder.builder().build()
        project.pluginManager.apply("java")

        assertDoesNotThrow { CodingRulesGradlePlugin().apply(project) }

        val task: Task = project.tasks.getByName("runStaticAnalysis")
        assertNotNull(task, "runStaticAnalysis task should be registered")

        val codeStyleTasks: Set<String> =
            Stream.of("checkstyleMain", "checkstyleTest", "pmdTest", "pmdMain").collect(toSet())
        assertTrue(
            task.dependsOn.containsAll(codeStyleTasks),
            format(
                "Task runStaticAnalysis should contain '%s' tasks, but actually: %s",
                codeStyleTasks,
                task.dependsOn
            )
        )

    }
}

/*
internal class CodingRulesGradlePluginPluginTest {
    @Test
    fun shouldApplyPluginSuccessfully() {

        val codeStyleTasks: Set<String> =
            Stream.of("checkstyleMain", "checkstyleTest", "pmdTest", "pmdMain").collect(toSet())
        assertTrue(
            task.getDependsOn().containsAll(codeStyleTasks),
            format(
                "Task runStaticAnalysis should contain '%s' tasks, but actually: %s",
                codeStyleTasks,
                task.getDependsOn()
            )
        )
    }
}

*/
