package pl.droidsonroids.composekit.composables.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Value used for both high and low contrast colors in the Material Design.
private const val DISABLED_ALPHA = 0.38f

@Composable
fun Color.disabled(): Color = copy(alpha = DISABLED_ALPHA)