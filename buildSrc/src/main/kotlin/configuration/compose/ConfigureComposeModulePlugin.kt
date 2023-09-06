package configuration.compose

import com.android.build.gradle.BaseExtension
import configuration.utils.deps
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ConfigureComposeModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        val baseExtension = extensions.getByName("android") as? BaseExtension ?: return

        baseExtension.applyComposeCommons(this)

        dependencies {
            "implementation"(deps.findBundle("compose.basic").get())
            "debugImplementation"(deps.findLibrary("androidX.compose.uiTooling").get())
        }
    }
}