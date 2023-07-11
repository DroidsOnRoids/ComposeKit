package configuration.android

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class ConfigureAndroidModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            plugins.apply("kotlin-android")

            val baseExtension = extensions.getByName("android") as? BaseExtension ?: return

            baseExtension.applyAndroidCommons(this)
        }
    }
}