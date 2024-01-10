package pl.droidsonroids.composekit.composables.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun Float.toDp(): Dp = this.let { pixels -> with(LocalDensity.current) { pixels.toDp() } }