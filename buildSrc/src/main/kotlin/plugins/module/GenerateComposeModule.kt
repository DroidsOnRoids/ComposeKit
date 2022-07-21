package plugins.module

import org.gradle.api.Project
import java.util.Locale

class GenerateComposeModule {

    private val generateModule = GenerateModule()

    @OptIn(ExperimentalStdlibApi::class)
    operator fun invoke(
        project: Project,
        nutshell: String,
    ) {
        check(nutshellRegex.matches(nutshell)) { "Nutshell can contain only letters, digits and dots. Was $nutshell" }
        val path = nutshell.getPath()
        val name = nutshell.getModuleName()
        val packageName = ComposePackageBase + nutshell.lowercase(Locale.ENGLISH)
        generateModule(project, name, packageName, path)
    }

    private fun String.getModuleName(): String = substring(lastIndexOf(".") + 1)

    private fun String.getPath(): String = substringBeforeLast('.', "").replace(".", "/")

    private companion object {

        val nutshellRegex = "[A-Za-z0-9.]*".toRegex()
        const val ComposePackageBase = "com.dor.compose.playground."
    }
}