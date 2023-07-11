package modulegenerator.projectfile

import java.io.File

internal object BuildGradle {

    private const val fileName = "build.gradle.kts"
    private const val basicLibrary = """plugins {
    id(deps.plugins.library.get().pluginId)
    id(deps.plugins.playgroundAndroid.get().pluginId)
    id(deps.plugins.playgroundKotlin.get().pluginId)
    id(deps.plugins.playgroundJava.get().pluginId)
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
