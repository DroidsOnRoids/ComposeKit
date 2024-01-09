plugins {
    with(libs.plugins) {
        alias(composekit.android.library)
        alias(composekit.android.library.compose)
    }
}

android {
    namespace = "com.dor.compose.playground.composables.theme"
}