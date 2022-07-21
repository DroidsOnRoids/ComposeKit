package plugins.module

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class GenerateComposeModuleTask : DefaultTask() {

    private val generateComposeModule = GenerateComposeModule()

    init {
        group = "build"
        description = "Generate empty compose module"
    }

    @Suppress("TooGenericExceptionCaught")
    @TaskAction
    fun generateModuleFromParams() {
        logger.lifecycle("Generating module...")

        try {
            val nutshell = project.findProperty(paramNutshell) as? String
                ?: throw IllegalStateException("-P$paramNutshell is an obligatory parameter when calling $taskName")

            generateComposeModule(project, nutshell)
        } catch (e: Exception) {
            throw IllegalStateException("${e.message}\n$usage", e)
        }

        logger.lifecycle("Module generated.")
    }

    companion object {

        const val taskName = "generateComposeModule"
        private const val paramNutshell = "nutshell"
        private const val usage = "Usage: ./gradlew $taskName " +
            "-P$paramNutshell=module.package.path.ModuleName" +
            "\n will generate module in path ./module/package/path/ModuleName " +
            "with package com.dor.compose.playground.module.package.path.modulename"
    }
}