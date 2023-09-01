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
    compileSdk = 33
    minSdk = 24

    execution {
        profiles {
            create("local") {
                r8 {
                    jvmOptions.addAll(listOf("-Xms2048m", "-Xmx8192m", "-XX:+HeapDumpOnOutOfMemoryError"))
                    runInSeparateProcess = true
                }
            }
        }
        defaultProfile = "local"
    }
}

include(":composables:button")
include(":composables:utils")
include(":composables:theme")
include(":composables:pullToRefresh")
include(":composables:foldableToolbar")
include(":composeDestinationsLibrary")
include(":composables:autoResizedText")
