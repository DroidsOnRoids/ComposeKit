package modulegenerator

import modulegenerator.utils.LinuxSeparatorProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.gradle.api.Project
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

internal class GenerateModuleTest {

    @get:Rule
    var testFolder: TemporaryFolder = TemporaryFolder()

    @MockK private lateinit var project: Project

    private val provider = LinuxSeparatorProvider
    private val generateModule = GenerateModule(provider)

    private val path = "test/module/path2d"
    private val moduleName = "testModule"
    private val packageName = "io.module.test2d"

    private val buildGradleFile = "build.gradle.kts"
    private val buildGradleContent =
        "plugins {\n    id(deps.plugins.library.get().pluginId)\n    id(deps.plugins.playgroundAndroid.get().pluginId)\n    id(deps.plugins.playgroundKotlin.get().pluginId)\n    id(deps.plugins.playgroundJava.get().pluginId)\n}\n\nandroid {\n    namespace = \"$packageName\"\n}\n\ndependencies {}"
    private val settingsGradleFile = "settings.gradle.kts"
    private val settingsGradleContent = "\n/* OK */ include(\":test:module:path2d:testModule\")"

    private val expectedDirTree = listOf(
        "/$settingsGradleFile",
        "/test",
        "/test/module",
        "/test/module/path2d",
        "/test/module/path2d/testModule",
        "/test/module/path2d/testModule/$buildGradleFile",
        "/test/module/path2d/testModule/src",
        "/test/module/path2d/testModule/src/main",
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
    }

    @Test
    fun `generate basic module structure`() {
        generateModule(project.rootDir, moduleName, packageName, path)

        val basePath = project.rootDir.path
        val actual = project.rootDir
            .walkTopDown()
            .map { it.path.removePrefix(basePath) }
            .filter { it.isNotBlank() }
            .toList()
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expectedDirTree)
        assertThat(project.rootDir.findSettingsGradle()).hasContent(settingsGradleContent)
        assertThat(project.rootDir.findBuildGradle()).hasContent(buildGradleContent)
    }

    private fun File.findSettingsGradle(): File =
        walkTopDown().first { it.path.endsWith(settingsGradleFile) }

    private fun File.findBuildGradle(): File =
        walkTopDown().first { it.path.endsWith(buildGradleFile) }
}
