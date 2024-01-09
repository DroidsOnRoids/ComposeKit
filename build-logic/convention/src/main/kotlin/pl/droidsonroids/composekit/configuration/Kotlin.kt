package pl.droidsonroids.composekit.configuration

import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import pl.droidsonroids.composekit.config.BuildConfig

internal fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = BuildConfig.JAVA_VERSION_NAME
        }
    }
}
