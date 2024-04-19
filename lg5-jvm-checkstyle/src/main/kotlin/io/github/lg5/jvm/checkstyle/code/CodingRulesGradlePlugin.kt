package io.github.lg5.jvm.checkstyle.code

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.quality.Checkstyle
import org.gradle.api.plugins.quality.CheckstyleExtension
import org.gradle.api.plugins.quality.Pmd
import org.gradle.api.plugins.quality.PmdExtension
import java.util.*
import java.util.stream.Stream


class CodingRulesGradlePlugin : Plugin<Project> {


    override fun apply(target: Project) {
        target.pluginManager.apply("checkstyle")
        target.extensions.configure(CheckstyleExtension::class.java) { checkstyleExtension ->
            checkstyleExtension.toolVersion = "10.12.1"
            checkstyleExtension.isIgnoreFailures = false
            checkstyleExtension.maxWarnings = 0
            checkstyleExtension.configFile = FileUtil.copyContentToTempFile(
                "style/checkstyle.xml",
                ".checkstyle.xml"
            )
        }

        target.pluginManager.apply("pmd")
        target.extensions.configure(PmdExtension::class.java) { pmdExtension ->
            pmdExtension.isConsoleOutput = true
            pmdExtension.toolVersion = "6.52.0"
            pmdExtension.isIgnoreFailures = false
            pmdExtension.ruleSets = emptyList()
            pmdExtension.ruleSetFiles = target.files(
                FileUtil.copyContentToTempFile(
                    "style/pmd.xml",
                    ".pmd.xml"
                )
            )
        }

        val checkstyleTaskNames: SortedSet<String> = target.tasks
            .withType(Checkstyle::class.java)
            .names

        val pmdTaskNames: SortedSet<String> = target.tasks
            .withType(Pmd::class.java)
            .names

        target.task(
            "runStaticAnalysis"
        ) { task ->
            task.setDependsOn(
                Stream.concat(
                    checkstyleTaskNames.stream(),
                    pmdTaskNames.stream()
                ).toList()
            )
        }
    }

}