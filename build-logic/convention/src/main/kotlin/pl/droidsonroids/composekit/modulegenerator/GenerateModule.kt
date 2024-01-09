package pl.droidsonroids.composekit.modulegenerator

import pl.droidsonroids.composekit.modulegenerator.projectfile.BuildGradle
import pl.droidsonroids.composekit.modulegenerator.projectfile.SettingsGradle
import pl.droidsonroids.composekit.modulegenerator.utils.SeparatorProvider
import pl.droidsonroids.composekit.utils.Logger
import java.io.File

internal class GenerateModule(private val separatorProvider: SeparatorProvider) {

    private val settingsGradle = SettingsGradle(separatorProvider)

    @Suppress("LongParameterList")
    operator fun invoke(
        rootDir: File,
        moduleName: String,
        packageName: String,
        path: String,
    ) {
        check(moduleNameRegex.matches(moduleName)) {
            "Module name can only contain letters and digits. Was $moduleName"
        }
        check(packageNameRegex.matches(packageName)) {
            "Package can only contain letters, digits and dot. Was $packageName"
        }
        check(pathRegex(separatorProvider).matches(path)) { "Path can only contain letters and slashes. Was $path" }

        Logger.i(
            "Generating module with: moduleName=$moduleName, packageName=$packageName, path=$path"
        )
        generateProjectDirectories(rootDir, moduleName, packageName, path)
    }

    private fun generateProjectDirectories(rootDir: File, moduleName: String, packageName: String, path: String) {
        val moduleDir = make(rootDir, "$path${separatorProvider.separator}$moduleName")

        generateSrcMain(moduleDir, packageName)
        generateSrcTest(moduleDir, packageName)
        BuildGradle.generate(moduleDir, packageName)
        settingsGradle.addModule(rootDir, path, moduleName)
    }

    private fun generateSrcMain(dir: File, packageName: String) {
        val mainDir = make(dir, "src${separatorProvider.separator}main")

        generateRes(mainDir)
        generateJava(mainDir, packageName)
    }

    private fun generateSrcTest(dir: File, packageName: String) {
        val testDir = make(dir, "src${separatorProvider.separator}test")

        generateJava(testDir, packageName)
    }

    private fun generateRes(dir: File) {
        val resDir = make(dir, "res")
        make(resDir, "layout")
        make(resDir, "values")
    }

    private fun generateJava(dir: File, packageName: String) {
        val javaDir = make(dir, "java")
        make(javaDir, packageName.toPath())
    }

    private fun make(baseDir: File, pathToCreate: String): File {
        val dirsToCreate = File(baseDir, pathToCreate)
        check(dirsToCreate.mkdirs()) { "Couldn't create ${dirsToCreate.path}." }
        return dirsToCreate
    }

    private fun String.toPath() = replace(".", separatorProvider.separator)

    private companion object {

        val moduleNameRegex = "[A-Za-z0-9.]*".toRegex()
        val packageNameRegex = "[a-z0-9.]*".toRegex()

        private val SeparatorProvider.regexSeparator: String
            get() = if (separator == "\\") """\\""" else separator

        fun pathRegex(separatorProvider: SeparatorProvider) =
            """[A-Za-z0-9${separatorProvider.regexSeparator}]*""".toRegex()
    }
}