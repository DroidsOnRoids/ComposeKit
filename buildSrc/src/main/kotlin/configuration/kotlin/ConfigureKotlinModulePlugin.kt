package configuration.kotlin

import config.BuildConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ConfigureKotlinModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = BuildConfig.JAVA_VERSION_NAME
            }
        }
    }
}