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

include(":composeDestinationsLibrary")
include(":composables:button")
include(":composables:utils")
include(":composables:theme")
include(":composables:pullToRefresh")
include(":composables:autoResizedText")
include(":composables:collapsing-toolbar")
include(":composables:foldableToolbar")
include(":composables:draganddroplist")
