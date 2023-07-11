package configuration.java

import config.BuildConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType

class ConfigureJavaModulePlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        tasks.withType(JavaCompile::class).configureEach {
            options.isFork = true
            sourceCompatibility = BuildConfig.JAVA_VERSION_NAME
            targetCompatibility = BuildConfig.JAVA_VERSION_NAME
        }
    }
}