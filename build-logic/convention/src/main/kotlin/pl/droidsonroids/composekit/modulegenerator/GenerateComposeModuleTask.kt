package pl.droidsonroids.composekit.modulegenerator

import org.gradle.api.DefaultTask
import org.gradle.api.file.ProjectLayout
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.TaskAction
import pl.droidsonroids.composekit.modulegenerator.utils.SystemSeparatorProvider
import pl.droidsonroids.composekit.utils.Logger
import javax.inject.Inject

abstract class GenerateComposeModuleTask : DefaultTask() {

    @get:Inject abstract val providerFactory: ProviderFactory
    @get:Inject abstract val projectLayout: ProjectLayout

    private val generateComposeModule = GenerateComposeModule(SystemSeparatorProvider())

    init {
        group = "build"
        description = "Generate empty compose module"
    }

    @Suppress("TooGenericExceptionCaught")
    @TaskAction
    fun generateModuleFromParams() {
        Logger.i("Generating module...")

        try {
            val nutshell = providerFactory.gradleProperty(paramNutshell).let {
                if (it.isPresent) it.get()
                else throw IllegalStateException("-P$paramNutshell is an obligatory parameter when calling $taskName")
            }

            generateComposeModule(projectLayout.projectDirectory.asFile, nutshell)
        } catch (e: Exception) {
            throw IllegalStateException("${e.message}\n$usage", e)
        }

        Logger.i("Module generated.")
    }

    companion object {

        const val taskName = "generateComposeModule"
        private const val paramNutshell = "nutshell"
        private const val usage = "Usage: ./gradlew $taskName " +
            "-P$paramNutshell=module.package.path.ModuleName" +
            "\n will generate module in path ./module/package/path/ModuleName " +
            "with package ${GenerateComposeModule.PackageBase}module.package.path.modulename"
    }
}