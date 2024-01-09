plugins {
    with(libs.plugins) {
        alias(composekit.android.library)
        alias(composekit.android.library.compose)
        alias(composekit.publishing)
    }
}

android {
    namespace = "com.dor.compose.playground.composables.utils"
}