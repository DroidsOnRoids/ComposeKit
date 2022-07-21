package plugins.module.projectfile

import java.io.File

object AndroidManifest {

    private const val fileName = "AndroidManifest.xml"
    private fun basic(packageName: String) = "<manifest package=\"$packageName\"/>"

    fun generate(dir: File, packageName: String) {
        val manifestFile = File(dir, fileName)
        manifestFile.createNewFile()
        manifestFile.writeText(basic(packageName))
    }
}