plugins {
    with(libs.plugins) {
        alias(composekit.android.library)
        alias(composekit.android.library.compose)
    }
}

android {
    namespace = "pl.droidsonroids.composekit.composables.theme"
}