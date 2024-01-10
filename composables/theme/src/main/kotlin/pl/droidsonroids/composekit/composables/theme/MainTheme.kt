package pl.droidsonroids.composekit.composables.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
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
                    colorScheme = LocalColors.current.materialColors,
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
 * [androidx.compose.material3.Typography] implementation which sets all colors to [debugTypography]
 * to discourage usage of [MaterialTheme.typography] in preference to [MainTheme.typography].
 */
fun debugTypography(
    debugTextStyle: TextStyle = TextStyle(fontSize = 34.sp),
) = androidx.compose.material3.Typography(
    displayLarge = debugTextStyle,
    displayMedium = debugTextStyle,
    displaySmall = debugTextStyle,
    headlineLarge = debugTextStyle,
    headlineMedium = debugTextStyle,
    headlineSmall = debugTextStyle,
    titleLarge = debugTextStyle,
    titleMedium = debugTextStyle,
    titleSmall = debugTextStyle,
    bodyLarge = debugTextStyle,
    bodyMedium = debugTextStyle,
    bodySmall = debugTextStyle,
    labelLarge = debugTextStyle,
    labelMedium = debugTextStyle,
    labelSmall = debugTextStyle,
)