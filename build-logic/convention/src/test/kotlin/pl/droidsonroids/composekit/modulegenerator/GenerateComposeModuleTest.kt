package pl.droidsonroids.composekit.modulegenerator

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.assertj.core.api.Assertions.assertThat
import org.gradle.api.Project
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import pl.droidsonroids.composekit.modulegenerator.utils.LinuxSeparatorProvider
import java.io.File

internal class GenerateComposeModuleTest {

    @get:Rule
    var testFolder: TemporaryFolder = TemporaryFolder()

    @MockK private lateinit var project: Project

    private val generateComposeModule = GenerateComposeModule(provider)

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every { project.rootDir } returns testFolder.newFolder("root")
    }

    @Test
    fun `generate compose module structure`() = testStructureGeneration(TestData.NestedModuleTestData)

    @Test
    fun `generate compose module structure under projects root`() = testStructureGeneration(TestData.RootDirModuleTestData)

    private fun testStructureGeneration(testData: TestData) {
        generateComposeModule(project.rootDir, testData.nutshell)

        val basePath = project.rootDir.path
        val actual = project.rootDir
            .walkTopDown()
            .map { it.path.removePrefix(basePath) }
            .filter { it.isNotBlank() }
            .toList()
        assertThat(actual).containsExactlyInAnyOrderElementsOf(testData.expectedDirTree)
        assertThat(project.rootDir.findSettingsGradle()).hasContent(testData.settingsGradleContent)
        assertThat(project.rootDir.findBuildGradle()).hasContent(testData.buildGradleContent)
    }

    private fun File.findSettingsGradle(): File =
        walkTopDown().first { it.path.endsWith(SETTINGS_GRADLE_FILE_NAME) }

    private fun File.findBuildGradle(): File =
        walkTopDown().first { it.path.endsWith(BUILD_GRADLE_FILE_NAME) }

    companion object {

        val provider = LinuxSeparatorProvider
        private const val BUILD_GRADLE_FILE_NAME = "build.gradle.kts"
        private const val SETTINGS_GRADLE_FILE_NAME = "settings.gradle.kts"
    }

    private interface TestData {

        val nutshell: String
        val buildGradleContent: String
        val settingsGradleContent: String
        val androidManifestContent: String
        val expectedDirTree: List<String>

        object NestedModuleTestData : TestData {

            override val nutshell = "testPath.deeper.testModule"
            override val buildGradleContent =
                "plugins {\n    id(libs.plugins.library.get().pluginId)\n    id(libs.plugins.playgroundAndroid.get().pluginId)\n    id(libs.plugins.playgroundKotlin.get().pluginId)\n    id(libs.plugins.playgroundJava.get().pluginId)\n}\n\nandroid {\n    namespace = \"com.dor.compose.playground.testpath.deeper.testmodule\"\n}\n\ndependencies {}"
            override val settingsGradleContent = "\n/* OK */ include(\":testPath:deeper:testModule\")"
            override val androidManifestContent = "<manifest package=\"com.dor.compose.playground.testpath.deeper.testmodule\"/>"

            override val expectedDirTree = listOf(
                "/$SETTINGS_GRADLE_FILE_NAME",
                "/testPath",
                "/testPath/deeper",
                "/testPath/deeper/testModule",
                "/testPath/deeper/testModule/$BUILD_GRADLE_FILE_NAME",
                "/testPath/deeper/testModule/src",
                "/testPath/deeper/testModule/src/main",
                "/testPath/deeper/testModule/src/main/res",
                "/testPath/deeper/testModule/src/main/res/layout",
                "/testPath/deeper/testModule/src/main/res/values",
                "/testPath/deeper/testModule/src/main/java",
                "/testPath/deeper/testModule/src/main/java/com",
                "/testPath/deeper/testModule/src/main/java/com/dor",
                "/testPath/deeper/testModule/src/main/java/com/dor/compose",
                "/testPath/deeper/testModule/src/main/java/com/dor/compose/playground",
                "/testPath/deeper/testModule/src/main/java/com/dor/compose/playground/testpath",
                "/testPath/deeper/testModule/src/main/java/com/dor/compose/playground/testpath/deeper",
                "/testPath/deeper/testModule/src/main/java/com/dor/compose/playground/testpath/deeper/testmodule",
                "/testPath/deeper/testModule/src/test",
                "/testPath/deeper/testModule/src/test/java",
                "/testPath/deeper/testModule/src/test/java/com",
                "/testPath/deeper/testModule/src/test/java/com/dor",
                "/testPath/deeper/testModule/src/test/java/com/dor/compose",
                "/testPath/deeper/testModule/src/test/java/com/dor/compose/playground",
                "/testPath/deeper/testModule/src/test/java/com/dor/compose/playground/testpath",
                "/testPath/deeper/testModule/src/test/java/com/dor/compose/playground/testpath/deeper",
                "/testPath/deeper/testModule/src/test/java/com/dor/compose/playground/testpath/deeper/testmodule"
            )
        }

        object RootDirModuleTestData : TestData {

            override val nutshell = "testModule"
            override val buildGradleContent =
                "plugins {\n    id(libs.plugins.library.get().pluginId)\n    id(libs.plugins.playgroundAndroid.get().pluginId)\n    id(libs.plugins.playgroundKotlin.get().pluginId)\n    id(libs.plugins.playgroundJava.get().pluginId)\n}\n\nandroid {\n    namespace = \"com.dor.compose.playground.testmodule\"\n}\n\ndependencies {}"
            override val settingsGradleContent = "\n/* OK */ include(\":testModule\")"
            override val androidManifestContent = "<manifest package=\"com.dor.compose.playground.testmodule\"/>"

            override val expectedDirTree = listOf(
                "/$SETTINGS_GRADLE_FILE_NAME",
                "/testModule",
                "/testModule/$BUILD_GRADLE_FILE_NAME",
                "/testModule/src",
                "/testModule/src/main",
                "/testModule/src/main/res",
                "/testModule/src/main/res/layout",
                "/testModule/src/main/res/values",
                "/testModule/src/main/java",
                "/testModule/src/main/java/com",
                "/testModule/src/main/java/com/dor",
                "/testModule/src/main/java/com/dor/compose",
                "/testModule/src/main/java/com/dor/compose/playground",
                "/testModule/src/main/java/com/dor/compose/playground/testmodule",
                "/testModule/src/test",
                "/testModule/src/test/java",
                "/testModule/src/test/java/com",
                "/testModule/src/test/java/com/dor",
                "/testModule/src/test/java/com/dor/compose",
                "/testModule/src/test/java/com/dor/compose/playground",
                "/testModule/src/test/java/com/dor/compose/playground/testmodule"
            )
        }
    }
}
