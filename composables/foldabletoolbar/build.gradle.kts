plugins {
    with(libs.plugins) {
        alias(composekit.android.library)
        alias(composekit.android.library.compose)
        alias(composekit.publishing)
    }
}

android {
    namespace = "pl.droidsonroids.composekit.composables.foldabletoolbar"
}

dependencies {
    implementation(projects.composables.utils)
}