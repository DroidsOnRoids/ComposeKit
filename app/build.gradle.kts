plugins {
    id(libs.plugins.application.get().pluginId)
    id(libs.plugins.playgroundAndroid.get().pluginId)
    id(libs.plugins.playgroundKotlin.get().pluginId)
    id(libs.plugins.playgroundJava.get().pluginId)
    id(libs.plugins.playgroundCompose.get().pluginId)
}

android {
    namespace = "com.dor.compose.playground"

    buildTypes {
        named("release").configure {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
        named("debug").configure {
            applicationIdSuffix = ".debug"
        }
    }

    signingConfigs {
        named("debug").configure {
            keyAlias = "android-debug"
            keyPassword = "android"
            storeFile = file("$rootDir/keystore/debug.keystore")
            storePassword = "android"
        }
    }
}

dependencies {
    implementation(libs.androidX.appCompat)
    implementation(libs.androidX.material)

    implementation(libs.androidX.compose.navigation)

    implementation(project(":composables:theme"))
    implementation(project(":composables:button"))
    implementation(project(":composables:pullToRefresh"))
    implementation(project(":composeDestinationsLibrary"))
    implementation(project(":composables:autoResizedText"))
    implementation(project(":composables:collapsing-toolbar"))
    implementation(project(":composables:foldableToolbar"))
    implementation(project(":composables:draganddroplist"))
}