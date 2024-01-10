package pl.droidsonroids.composekit.configuration

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension
import pl.droidsonroids.composekit.config.BuildConfig

internal fun Project.configureKotlin() {
    kotlinExtension.apply {
        jvmToolchain(BuildConfig.JAVA_VERSION)
    }
}
