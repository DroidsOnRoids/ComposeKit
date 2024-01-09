package pl.droidsonroids.composekit.modulegenerator.projectfile

import pl.droidsonroids.composekit.modulegenerator.utils.SeparatorProvider
import java.io.File

internal class SettingsGradle(private val separatorProvider: SeparatorProvider) {

    fun addModule(rootDir: File, path: String, moduleName: String) {
        val settingGradleFile = File(rootDir, fileName)
        settingGradleFile.createNewFile()
        settingGradleFile.appendText(include(modulePathFrom(path, moduleName)))
    }

    private fun include(modulePath: String) = "\n/* OK */ include(\"$modulePath\")"

    /**
     * removeSuffix(":") fixes issue with paths ending with '/'
     *
     */
    private fun modulePathFrom(path: String, moduleName: String): String {
        val modulePathPrefix = path.replace(separatorProvider.separator, ":").removeSuffix(":")
        return if (modulePathPrefix.isEmpty()) ":$moduleName" else ":$modulePathPrefix:$moduleName"
    }

    companion object {

        private const val fileName = "settings.gradle.kts"
    }
}