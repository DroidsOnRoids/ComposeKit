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
include(":composables:pulltorefresh")
include(":composables:autoresizedtext")
include(":composables:collapsingtoolbar")
include(":composables:foldabletoolbar")
include(":composables:draganddroplist")
