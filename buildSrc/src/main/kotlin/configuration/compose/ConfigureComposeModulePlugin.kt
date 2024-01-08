package configuration.compose

import com.android.build.gradle.BaseExtension
import configuration.utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ConfigureComposeModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        val baseExtension = extensions.getByName("android") as? BaseExtension ?: return

        baseExtension.applyComposeCommons(this)

        dependencies {
            "implementation"(platform(libs.findLibrary("androidX.compose.bom").get()))
            "implementation"(libs.findBundle("compose.basic").get())
            "debugImplementation"(libs.findLibrary("androidX.compose.uiTooling").get())
        }
    }
}