import com.android.build.api.dsl.SettingsExtension

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        gradlePluginPortal()
    }

    versionCatalogs {
        create("deps") {
            from(files("$rootDir/gradle/dependencies.toml"))
        }
    }
}

include(":app")

plugins {
    id("com.android.settings") version "8.0.2"
}

@Suppress("UnstableApiUsage")
extensions.getByType(SettingsExtension::class).apply {
    compileSdk = 34
    minSdk = 24
}

include(":composeDestinationsLibrary")
include(":composables:button")
include(":composables:utils")
include(":composables:theme")
include(":composables:pullToRefresh")
include(":composables:autoResizedText")
include(":composables:collapsing-toolbar")
include(":composables:draganddroplist")
