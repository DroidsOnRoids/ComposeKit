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

internal class GenerateComposeModuleTest {

    @get:Rule
    var testFolder: TemporaryFolder = TemporaryFolder()

    @MockK(relaxed = true) private lateinit var logger: Logger
    @MockK private lateinit var project: Project

    private val generateComposeModule = GenerateComposeModule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every { project.rootDir } returns testFolder.newFolder("root")
        every { project.logger } returns logger
    }

    @Test
    fun `generate compose module structure`() = testStructureGeneration(TestData.NestedModuleTestData)

    @Test
    fun `generate compose module structure under projects root`() = testStructureGeneration(TestData.RootDirModuleTestData)

    private fun testStructureGeneration(testData: TestData) {
        generateComposeModule(project, testData.nutshell)

        val basePath = project.rootDir.path
        val actual = project.rootDir
            .walkTopDown()
            .map { it.path.removePrefix(basePath) }
            .filter { it.isNotBlank() }
            .toList()
        assertThat(actual).containsExactlyInAnyOrderElementsOf(testData.expectedDirTree)
        assertThat(project.rootDir.findManifest()).hasContent(testData.androidManifestContent)
        assertThat(project.rootDir.findSettingsGradle()).hasContent(testData.settingsGradleContent)
        assertThat(project.rootDir.findBuildGradle()).hasContent(testData.buildGradleContent)
    }

    private fun File.findManifest(): File =
        walkTopDown().first { it.path.endsWith(ANDROID_MANIFEST_FILE_NAME) }

    private fun File.findSettingsGradle(): File =
        walkTopDown().first { it.path.endsWith(SETTINGS_GRADLE_FILE_NAME) }

    private fun File.findBuildGradle(): File =
        walkTopDown().first { it.path.endsWith(BUILD_GRADLE_FILE_NAME) }

    companion object {

        private const val BUILD_GRADLE_FILE_NAME = "build.gradle"
        private const val SETTINGS_GRADLE_FILE_NAME = "settings.gradle.kts"
        private const val ANDROID_MANIFEST_FILE_NAME = "AndroidManifest.xml"
    }

    private interface TestData {

        val nutshell: String
        val buildGradleContent: String
        val settingsGradleContent: String
        val androidManifestContent: String
        val expectedDirTree: List<String>

        object NestedModuleTestData : TestData {

            override val nutshell = "testPath.deeper.testModule"
            override val buildGradleContent = "apply plugin: 'com.android.library'\n\ndependencies {}"
            override val settingsGradleContent = "\ninclude(\":testPath:deeper:testModule\")"
            override val androidManifestContent = "<manifest package=\"com.dor.compose.playground.testpath.deeper.testmodule\"/>"

            override val expectedDirTree = listOf(
                "/$SETTINGS_GRADLE_FILE_NAME",
                "/testPath",
                "/testPath/deeper",
                "/testPath/deeper/testModule",
                "/testPath/deeper/testModule/$BUILD_GRADLE_FILE_NAME",
                "/testPath/deeper/testModule/src",
                "/testPath/deeper/testModule/src/main",
                "/testPath/deeper/testModule/src/main/$ANDROID_MANIFEST_FILE_NAME",
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
            override val buildGradleContent = "apply plugin: 'com.android.library'\n\ndependencies {}"
            override val settingsGradleContent = "\ninclude(\":testModule\")"
            override val androidManifestContent = "<manifest package=\"com.dor.compose.playground.testmodule\"/>"

            override val expectedDirTree = listOf(
                "/$SETTINGS_GRADLE_FILE_NAME",
                "/testModule",
                "/testModule/$BUILD_GRADLE_FILE_NAME",
                "/testModule/src",
                "/testModule/src/main",
                "/testModule/src/main/$ANDROID_MANIFEST_FILE_NAME",
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
