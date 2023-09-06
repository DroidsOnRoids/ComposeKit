package configuration.compose

import com.android.build.gradle.BaseExtension
import configuration.utils.deps
import org.gradle.api.Project

internal fun BaseExtension.applyComposeCommons(project: Project) {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = project.deps.findVersion("androidXComposeCompiler").get().requiredVersion
    }
}