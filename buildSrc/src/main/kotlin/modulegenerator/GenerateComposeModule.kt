package modulegenerator

import modulegenerator.utils.SeparatorProvider
import java.io.File
import java.util.Locale

internal class GenerateComposeModule(private val separatorProvider: SeparatorProvider) {

    private val generateModule = GenerateModule(separatorProvider)

    @OptIn(ExperimentalStdlibApi::class)
    operator fun invoke(
        rootDir: File,
        nutshell: String,
    ) {
        check(nutshellRegex.matches(nutshell)) { "Nutshell can contain only letters, digits and dots. Was $nutshell" }
        val path = nutshell.getPath()
        val name = nutshell.getModuleName()
        val packageName = PlaygroundPackageBase + nutshell.lowercase(Locale.ENGLISH)
        generateModule(rootDir, name, packageName, path)
    }

    private fun String.getModuleName(): String = substring(lastIndexOf(".") + 1)

    private fun String.getPath(): String = substringBeforeLast('.', "").replace(".", separatorProvider.separator)

    private companion object {

        val nutshellRegex = "[A-Za-z0-9.]*".toRegex()
        const val PlaygroundPackageBase = "com.dor.compose.playground."
    }
}