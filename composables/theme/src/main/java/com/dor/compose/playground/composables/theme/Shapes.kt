package com.dor.compose.playground.composables.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

internal val Shapes = Shapes(
    small = RoundedCornerShape(3.dp)
)

@Composable
fun ProvideShapes(
    shapes: Shapes = Shapes,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalShapes provides shapes, content = content)
}

internal val LocalShapes = staticCompositionLocalOf<Shapes> {
    error("No shapes provided")
}
