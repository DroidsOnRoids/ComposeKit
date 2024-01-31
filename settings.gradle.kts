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

rootProject.name = "ComposeKit"

include(":app")

include(":composables:utils")
include(":composables:theme")
include(":composables:pullToRefresh")
include(":composables:autoresizedtext")
include(":composables:collapsingtoolbar")
include(":composables:foldableToolbar")
include(":composables:draganddroplist")
