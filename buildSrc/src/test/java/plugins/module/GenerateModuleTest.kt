package plugins.module

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

internal class GenerateModuleTest {

    @get:Rule
    var testFolder: TemporaryFolder = TemporaryFolder()

    @MockK(relaxed = true) private lateinit var logger: Logger
    @MockK private lateinit var project: Project

    private val generateModule = GenerateModule()

    private val path = "test/module/path2d"
    private val moduleName = "testModule"
    private val packageName = "io.module.test2d"

    private val buildGradleFile = "build.gradle"
    private val buildGradleContent = "apply plugin: 'com.android.library'\n\ndependencies {}"
    private val settingsGradleFile = "settings.gradle.kts"
    private val settingsGradleContent = "\ninclude(\":test:module:path2d:testModule\")"
    private val androidManifestFile = "AndroidManifest.xml"
    private val androidManifestContent = "<manifest package=\"$packageName\"/>"

    private val expectedDirTree = listOf(
        "/$settingsGradleFile",
        "/test",
        "/test/module",
        "/test/module/path2d",
        "/test/module/path2d/testModule",
        "/test/module/path2d/testModule/$buildGradleFile",
        "/test/module/path2d/testModule/src",
        "/test/module/path2d/testModule/src/main",
        "/test/module/path2d/testModule/src/main/$androidManifestFile",
        "/test/module/path2d/testModule/src/main/res",
        "/test/module/path2d/testModule/src/main/res/layout",
        "/test/module/path2d/testModule/src/main/res/values",
        "/test/module/path2d/testModule/src/main/java",
        "/test/module/path2d/testModule/src/main/java/io",
        "/test/module/path2d/testModule/src/main/java/io/module",
        "/test/module/path2d/testModule/src/main/java/io/module/test2d",
        "/test/module/path2d/testModule/src/test",
        "/test/module/path2d/testModule/src/test/java",
        "/test/module/path2d/testModule/src/test/java/io",
        "/test/module/path2d/testModule/src/test/java/io/module",
        "/test/module/path2d/testModule/src/test/java/io/module/test2d"
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        val testProjectDir: File = testFolder.newFolder("root")
        every { project.rootDir } returns testProjectDir
        every { project.logger } returns logger
    }

    @Test
    fun `generate basic module structure`() {
        generateModule(project, moduleName, packageName, path)

        val basePath = project.rootDir.path
        val actual = project.rootDir
            .walkTopDown()
            .map { it.path.removePrefix(basePath) }
            .filter { it.isNotBlank() }
            .toList()
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expectedDirTree)
        assertThat(project.rootDir.findManifest()).hasContent(androidManifestContent)
        assertThat(project.rootDir.findSettingsGradle()).hasContent(settingsGradleContent)
        assertThat(project.rootDir.findBuildGradle()).hasContent(buildGradleContent)
    }

    private fun File.findManifest(): File =
        walkTopDown().first { it.path.endsWith(androidManifestFile) }

    private fun File.findSettingsGradle(): File =
        walkTopDown().first { it.path.endsWith(settingsGradleFile) }

    private fun File.findBuildGradle(): File =
        walkTopDown().first { it.path.endsWith(buildGradleFile) }
}
