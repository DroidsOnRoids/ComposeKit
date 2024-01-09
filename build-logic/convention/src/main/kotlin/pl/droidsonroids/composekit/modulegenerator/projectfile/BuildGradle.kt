package pl.droidsonroids.composekit.modulegenerator.projectfile

import java.io.File

internal object BuildGradle {

    private const val fileName = "build.gradle.kts"
    private const val basicLibrary = """plugins {
    id(libs.plugins.library.get().pluginId)
    id(libs.plugins.playgroundAndroid.get().pluginId)
    id(libs.plugins.playgroundKotlin.get().pluginId)
    id(libs.plugins.playgroundJava.get().pluginId)
}
""" // No trim

    private const val basicDependencies = "dependencies {}"
    private fun basicAndroid(packageName: String) = "android {\n    namespace = \"$packageName\"\n}"

    fun generate(dir: File, packageName: String) {
        val buildGradleFile = File(dir, fileName)
        buildGradleFile.createNewFile()
        buildGradleFile.writeText("$basicLibrary\n${basicAndroid(packageName)}\n\n$basicDependencies")
    }
}
