package plugins.module

import org.gradle.api.Project
import plugins.module.projectfile.AndroidManifest
import plugins.module.projectfile.BuildGradle
import plugins.module.projectfile.SettingsGradle
import java.io.File

/**
 * Copied from different porject, could be changed for compose project
 * TODO:
 * - new package regex """^(?:[a-zA-Z]+(?:\d*[a-zA-Z_]*)*)(?:\.[a-zA-Z]+(?:\d*[a-zA-Z_]*)*)*${'$'}""" and tests
 * - Make regexes compatible with giWindows (pathRegex)
 * - Automatically group modules in settings.gradle and sort them alphabetically
 */
class GenerateModule {

    @Suppress("LongParameterList")
    operator fun invoke(
        project: Project,
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
        check(pathRegex.matches(path)) { "Path can only contain letters and slashes. Was $path" }

        project.logger.lifecycle(
            "Generating module with: moduleName=$moduleName, packageName=$packageName, path=$path."
        )
        generateProjectDirectories(project.rootDir, moduleName, packageName, path)
    }

    private fun generateProjectDirectories(rootDir: File, moduleName: String, packageName: String, path: String) {
        val moduleDir = make(rootDir, "$path/$moduleName")

        generateSrcMain(moduleDir, packageName)
        generateSrcTest(moduleDir, packageName)
        BuildGradle.generate(moduleDir)
        SettingsGradle.addModule(rootDir, path, moduleName)
    }

    private fun generateSrcMain(dir: File, packageName: String) {
        val mainDir = make(dir, "src/main")

        generateRes(mainDir)
        generateJava(mainDir, packageName)
        AndroidManifest.generate(mainDir, packageName)
    }

    private fun generateSrcTest(dir: File, packageName: String) {
        val testDir = make(dir, "src/test")

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

    private fun String.toPath() = replace(".", "/")

    private companion object {

        val moduleNameRegex = "[A-Za-z0-9.]*".toRegex()
        val packageNameRegex = "[a-z0-9.]*".toRegex()
        val pathRegex = "[A-Za-z0-9/]*".toRegex()
    }
}