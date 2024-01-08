package com.dor.compose.playground.composables.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
object CustomColors {

    val Gray = Color(0xFFE9E9E9)
    val Red = Color(0xFFd91010)
    val SoftPrimary = Color(0xFF6A6A6A)
    val DarkGrey = Color(0xFF3B3B3B)
}

@Immutable
@Suppress("LongParameterList")
class Colors(
    val primary: Color = Color.Black,
    val onPrimary: Color = Color.White,
    val primaryContainer: Color = CustomColors.Gray,
    val onPrimaryContainer: Color = Color.Black,
    val secondary: Color = Color.Black,
    val onSecondary: Color = Color.White,
    val secondaryContainer: Color = CustomColors.Gray,
    val onSecondaryContainer: Color = Color.Black,
    val background: Color = Color.White,
    val onBackground: Color = Color.Black,
    val surface: Color = Color.White,
    val onSurface: Color = Color.Black,
    val error: Color = CustomColors.Red,
    val onError: Color = Color.White,
    val primaryVariant: Color = CustomColors.DarkGrey,
    val primaryVariantLow: Color = CustomColors.SoftPrimary,
    val secondaryVariant: Color = CustomColors.DarkGrey,
    val secondaryVariantLow: Color = CustomColors.SoftPrimary,
    val dividerColor: Color = CustomColors.DarkGrey.copy(alpha = 0.2f),
) {

    val materialColors = lightColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        error = error,
        onError = onError,
    )
}

@Composable
fun ProvideColors(
    colors: Colors = Colors(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalColors provides colors,
        content = content
    )
}

internal val LocalColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}
