package pl.droidsonroids.composekit.modulegenerator.projectfile

import pl.droidsonroids.composekit.modulegenerator.ModuleType
import java.io.File

internal object BuildGradle {

    private const val fileName = "build.gradle.kts"
    private const val androidLibrary = """plugins {
    alias(libs.plugins.composekit.android.library)
}
""" // No trim
    private const val composeLibrary = """plugins {
    with(libs.plugins) {
        alias(composekit.android.library)
        alias(composekit.android.library.compose)
    }
}
""" // No trim

    private const val basicDependencies = "dependencies {}"
    private fun basicAndroid(packageName: String) = "android {\n    namespace = \"$packageName\"\n}"

    fun generate(dir: File, packageName: String, type: ModuleType) {
        val buildGradleFile = File(dir, fileName)
        buildGradleFile.createNewFile()
        buildGradleFile.writeText("${type.getPluginsBlock()}\n${basicAndroid(packageName)}\n\n$basicDependencies")
    }

    private fun ModuleType.getPluginsBlock(): String = when (this) {
        ModuleType.AndroidLibrary -> androidLibrary
        ModuleType.ComposeLibrary -> composeLibrary
    }
}
