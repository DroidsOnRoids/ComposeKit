package plugins.module

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class GenerateModuleTask : DefaultTask() {

    private val generateModule = GenerateModule()

    init {
        group = "build"
        description = "Generate empty module"
    }

    @Suppress("TooGenericExceptionCaught")
    @TaskAction
    fun generateModuleFromParams() {
        logger.lifecycle("Generating module...")

        try {
            val modulePath = project.findProperty(paramModulePath) as? String ?: "./"

            val moduleName = project.findProperty(paramModuleName) as? String
                ?: throw IllegalStateException("-P$paramModuleName is an obligatory parameter when calling $taskName")

            val packageName = project.findProperty(paramPackage) as? String
                ?: throw IllegalStateException("-P$paramPackage is an obligatory parameter when calling $taskName")

            generateModule(project, moduleName, packageName, modulePath)
        } catch (e: Exception) {
            throw IllegalStateException("${e.message}\n$usage", e)
        }

        logger.lifecycle("Module generated.")
    }

    companion object {

        const val taskName = "generateModule"
        private const val paramModulePath = "modulePath"
        private const val paramModuleName = "moduleName"
        private const val paramPackage = "package"
        private const val usage = "Usage: ./gradlew $taskName " +
            "[-P$paramModulePath=./other/] " +
            "-P$paramModuleName=moduleName " +
            "-P$paramPackage=module.name.package" +
            "\n will generate module in path ./other/ModuleName with package module.name.package"
    }
}