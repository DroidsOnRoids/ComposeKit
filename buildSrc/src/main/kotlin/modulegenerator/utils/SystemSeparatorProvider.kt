package modulegenerator.utils

import java.io.File

internal class SystemSeparatorProvider : SeparatorProvider {

    override val separator: String
        get() = File.separator
    override val separatorChar: Char
        get() = File.separatorChar
}