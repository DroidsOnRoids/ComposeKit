package com.dor.compose.playground.composables.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun MainTheme(
    content: @Composable () -> Unit,
) {
    ProvideShapes {
        ProvideColors {
            ProvideTypography {
                MaterialTheme(
                    colors = LocalColors.current.materialColors,
                    typography = debugTypography(),
                    shapes = LocalShapes.current,
                    content = content,
                )
            }
        }
    }
}

object MainTheme {

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

/**
 * A Material [Typography] implementation which sets all colors to [debugTypography] to discourage usage of
 * [MaterialTheme.typography] in preference to [MainTheme.typography].
 */
fun debugTypography(
    debugTextStyle: TextStyle = TextStyle(fontSize = 34.sp),
) = androidx.compose.material.Typography(
    h1 = debugTextStyle,
    h2 = debugTextStyle,
    h3 = debugTextStyle,
    h4 = debugTextStyle,
    h5 = debugTextStyle,
    h6 = debugTextStyle,
    subtitle1 = debugTextStyle,
    subtitle2 = debugTextStyle,
    body1 = debugTextStyle,
    body2 = debugTextStyle,
    button = debugTextStyle,
    caption = debugTextStyle,
    overline = debugTextStyle,
)