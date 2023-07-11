package configuration.android

import com.android.build.gradle.BaseExtension
import config.BuildConfig
import org.gradle.api.Project
import utils.ContinuousIntegration

internal fun BaseExtension.applyAndroidCommons(project: Project) = apply {
    defaultConfig {
        targetSdk = BuildConfig.TARGET_SDK
        vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
        sourceCompatibility = BuildConfig.JAVA_VERSION
        targetCompatibility = BuildConfig.JAVA_VERSION
    }

    lintOptions {
        isAbortOnError = false
        isCheckDependencies = true
        isCheckReleaseBuilds = ContinuousIntegration.isCi()
        xmlOutput = project.rootProject.file("build/reports/lint/lint-results.xml")
        disable("ContentDescription", "PrivateResource", "Typos")
    }

    buildFeatures.viewBinding = true

    packagingOptions {
        exclude("META-INF/**")
    }
}