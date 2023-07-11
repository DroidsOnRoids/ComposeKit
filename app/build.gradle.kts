plugins {
    id(deps.plugins.application.get().pluginId)
    id(deps.plugins.playgroundAndroid.get().pluginId)
    id(deps.plugins.playgroundKotlin.get().pluginId)
    id(deps.plugins.playgroundJava.get().pluginId)
}

android {
    namespace = "com.dor.compose.playground"

    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = deps.versions.androidXComposeCompiler.get()
    }


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
    implementation(deps.androidX.appCompat)
    implementation(deps.androidX.material)

    implementation(deps.androidX.compose.runtime)
    implementation(deps.androidX.compose.material)
    implementation(deps.androidX.compose.foundation)
    implementation(deps.androidX.compose.navigation)

    implementation(project(":composables:theme"))
    implementation(project(":composables:button"))
    implementation(project(":composables:pullToRefresh"))
    implementation(project(":composeDestinationsLibrary"))
    implementation(project(":composables:autoResizedText"))
}