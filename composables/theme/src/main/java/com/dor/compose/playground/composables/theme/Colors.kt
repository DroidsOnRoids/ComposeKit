package com.dor.compose.playground.composables.theme

import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
object CustomColors {

    val Gray = Color(0xFFE9E9E9)
    val Red = Color(0xFFd91010)
    val SoftPrimary = Color(0xFFFAFAFA)
    val DarkGrey = Color(0xFFC2C2C2)
}

@Immutable
@Suppress("LongParameterList")
class Colors(
    val primary: Color = Color.Black,
    val primaryVariant: Color = CustomColors.Gray,
    val secondary: Color = Color.Black,
    val secondaryVariant: Color = CustomColors.Gray,
    val background: Color = Color.White,
    val surface: Color = Color.White,
    val error: Color = CustomColors.Red,
    val onPrimary: Color = Color.White,
    val onSecondary: Color = Color.White,
    val onBackground: Color = Color.Black,
    val onSurface: Color = Color.Black,
    val onError: Color = Color.White,
    val onSecondaryVariant: Color = Color.Black,
    val primaryVariantLight: Color = CustomColors.SoftPrimary,
    val primaryVariantDark: Color = CustomColors.DarkGrey,
    val secondaryVariantLight: Color = CustomColors.SoftPrimary,
    val secondaryVariantDark: Color = CustomColors.DarkGrey,
    val dividerColor: Color = CustomColors.DarkGrey.copy(alpha = 0.2f),
) {

    val materialColors = lightColors(
        primary = primary,
        primaryVariant = primaryVariant,
        secondary = secondary,
        secondaryVariant = secondaryVariant,
        background = background,
        surface = surface,
        error = error,
        onPrimary = onPrimary,
        onSecondary = onSecondary,
        onBackground = onBackground,
        onSurface = onSurface,
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
        LocalContentAlpha provides 1f,
        content = content
    )
}

internal val LocalColors = staticCompositionLocalOf<Colors> {
    error("No colors provided")
}
