import com.android.build.api.dsl.SettingsExtension

pluginManagement {
    includeBuild("build-logic")
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
}

include(":app")

plugins {
    id("com.android.settings") version "8.2.1"
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
include(":composables:foldableToolbar")
include(":composables:draganddroplist")
