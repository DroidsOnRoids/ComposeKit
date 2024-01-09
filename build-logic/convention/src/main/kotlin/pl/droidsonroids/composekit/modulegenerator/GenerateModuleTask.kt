package pl.droidsonroids.composekit.modulegenerator

import org.gradle.api.DefaultTask
import org.gradle.api.file.ProjectLayout
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.TaskAction
import pl.droidsonroids.composekit.modulegenerator.utils.SystemSeparatorProvider
import javax.inject.Inject

abstract class GenerateModuleTask : DefaultTask() {

    @get:Inject abstract val providerFactory: ProviderFactory
    @get:Inject abstract val projectLayout: ProjectLayout

    private val generateModule = GenerateModule(SystemSeparatorProvider())

    init {
        group = "build"
        description = "Generate empty module"
    }

    @Suppress("TooGenericExceptionCaught")
    @TaskAction
    fun generateModuleFromParams() {
        logger.lifecycle("Generating module...")

        try {
            val modulePath = providerFactory.gradleProperty(paramModulePath).let {
                if (it.isPresent) it.get() else "./"
            }

            val moduleName = providerFactory.gradleProperty(paramModuleName).let {
                if (it.isPresent) it.get()
                else throw IllegalStateException("-P$paramModuleName is an obligatory parameter when calling $taskName")
            }

            val packageName = providerFactory.gradleProperty(paramPackage).let {
                if (it.isPresent) it.get()
                else throw IllegalStateException("-P$paramPackage is an obligatory parameter when calling $taskName")
            }

            generateModule(projectLayout.projectDirectory.asFile, moduleName, packageName, modulePath)
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