plugins {
    with(libs.plugins) {
        alias(composekit.android.app)
        alias(composekit.android.app.compose)
    }
}

android {
    namespace = "pl.droidsonroids.composekit"

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

    implementation(projects.composables.pulltorefresh)
    implementation(projects.composables.theme)

    implementation(libs.composeKit.autoResizedText)
    implementation(libs.composeKit.collapsingToolbar)
    implementation(libs.composeKit.dragAndDropList)
    implementation(libs.composeKit.foldableToolbar)
}