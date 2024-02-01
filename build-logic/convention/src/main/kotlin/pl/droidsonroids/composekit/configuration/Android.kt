package pl.droidsonroids.composekit.configuration

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import pl.droidsonroids.composekit.utils.ContinuousIntegration

internal fun Project.applyAndroidCommons(
    extension: CommonExtension<*, *, *, *, *>,
) = extension.apply {
    compileSdk = 34

    defaultConfig {
        minSdk = 21
        vectorDrawables.useSupportLibrary = true
    }

    lint {
        abortOnError = false
        checkDependencies = true
        checkReleaseBuilds = ContinuousIntegration.isCi()
        xmlOutput = project.rootProject.file("build/reports/lint/lint-results.xml")
        disable.addAll(listOf("ContentDescription", "PrivateResource", "Typos"))
    }

    buildFeatures.viewBinding = true
}
