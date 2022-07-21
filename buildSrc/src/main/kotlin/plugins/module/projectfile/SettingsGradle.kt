package plugins.module.projectfile

import java.io.File

object SettingsGradle {

    private const val fileName = "settings.gradle.kts"

    fun addModule(rootDir: File, path: String, moduleName: String) {
        val settingGradleFile = File(rootDir, fileName)
        settingGradleFile.createNewFile()
        settingGradleFile.appendText(SettingsGradle.include(modulePathFrom(path, moduleName)))
    }

    private fun include(modulePath: String) = "\ninclude(\"$modulePath\")"

    /**
     * removeSuffix(":") fixes issue with paths that ends with '/'
     *
     */
    private fun modulePathFrom(path: String, moduleName: String): String {
        val modulePathPrefix = path.replace("/", ":").removeSuffix(":")
        return if (modulePathPrefix.isEmpty()) ":$moduleName" else ":$modulePathPrefix:$moduleName"
    }
}