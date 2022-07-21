package plugins.module.projectfile

import java.io.File

object BuildGradle {

    private const val fileName = "build.gradle"
    private const val basicLibrary = "apply plugin: 'com.android.library'"
    private const val basicDependencies = "dependencies {}"

    fun generate(dir: File) {
        val buildGradleFile = File(dir, fileName)
        buildGradleFile.createNewFile()
        buildGradleFile.writeText("$basicLibrary\n\n$basicDependencies")
    }
}
