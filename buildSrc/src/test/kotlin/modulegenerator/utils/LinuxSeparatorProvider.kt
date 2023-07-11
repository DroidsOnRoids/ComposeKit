package modulegenerator.utils

object LinuxSeparatorProvider : SeparatorProvider {

    override val separator: String = "/"
    override val separatorChar: Char = '/'
}