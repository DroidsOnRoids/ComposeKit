package pl.droidsonroids.composekit.modulegenerator.utils

internal object LinuxSeparatorProvider : SeparatorProvider {

    override val separator: String = "/"
    override val separatorChar: Char = '/'
}