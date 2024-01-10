package pl.droidsonroids.composekit.composables.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
@Suppress("LongParameterList")
class Typography private constructor(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
    val button: TextStyle,
) {

    @Suppress("LongParameterList")
    constructor(
        defaultFont: FontFamily = FontFamily.SansSerif,
        displayLarge: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.0.sp,
            letterSpacing = (-0.2).sp,
        ),
        displayMedium: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.0.sp,
        ),
        displaySmall: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.0.sp,
        ),
        headlineLarge: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.0.sp,
        ),
        headlineMedium: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.0.sp,
        ),
        headlineSmall: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.0.sp,
        ),
        titleLarge: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.0.sp,
        ),
        titleMedium: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.0.sp,
            letterSpacing = 0.2.sp,
        ),
        titleSmall: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.0.sp,
            letterSpacing = 0.1.sp,
        ),
        bodyLarge: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.0.sp,
            letterSpacing = 0.5.sp,
        ),
        bodyMedium: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.0.sp,
            letterSpacing = 0.2.sp,
        ),
        bodySmall: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.0.sp,
            letterSpacing = 0.4.sp,
        ),
        labelLarge: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.0.sp,
            letterSpacing = 0.1.sp,
        ),
        labelMedium: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.0.sp,
            letterSpacing = 0.5.sp,
        ),
        labelSmall: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.0.sp,
            letterSpacing = 0.5.sp,
        ),
        button: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        )
    ) : this(
        displayLarge = displayLarge.withDefaultFont(defaultFont),
        displayMedium = displayMedium.withDefaultFont(defaultFont),
        displaySmall = displaySmall.withDefaultFont(defaultFont),
        headlineLarge = headlineLarge.withDefaultFont(defaultFont),
        headlineMedium = headlineMedium.withDefaultFont(defaultFont),
        headlineSmall = headlineSmall.withDefaultFont(defaultFont),
        titleLarge = titleLarge.withDefaultFont(defaultFont),
        titleMedium = titleMedium.withDefaultFont(defaultFont),
        titleSmall = titleSmall.withDefaultFont(defaultFont),
        bodyLarge = bodyLarge.withDefaultFont(defaultFont),
        bodyMedium = bodyMedium.withDefaultFont(defaultFont),
        bodySmall = bodySmall.withDefaultFont(defaultFont),
        labelLarge = labelLarge.withDefaultFont(defaultFont),
        labelMedium = labelMedium.withDefaultFont(defaultFont),
        labelSmall = labelSmall.withDefaultFont(defaultFont),
        button = button.withDefaultFont(defaultFont),
    )

    @Suppress("LongParameterList")
    fun copy(
        displayLarge: TextStyle = this.displayLarge,
        displayMedium: TextStyle = this.displayMedium,
        displaySmall: TextStyle = this.displaySmall,
        headlineLarge: TextStyle = this.headlineLarge,
        headlineMedium: TextStyle = this.headlineMedium,
        headlineSmall: TextStyle = this.headlineSmall,
        titleLarge: TextStyle = this.titleLarge,
        titleMedium: TextStyle = this.titleMedium,
        titleSmall: TextStyle = this.titleSmall,
        bodyLarge: TextStyle = this.bodyLarge,
        bodyMedium: TextStyle = this.bodyMedium,
        bodySmall: TextStyle = this.bodySmall,
        labelLarge: TextStyle = this.labelLarge,
        labelMedium: TextStyle = this.labelMedium,
        labelSmall: TextStyle = this.labelSmall,
        button: TextStyle = this.button,
    ): Typography = Typography(
        displayLarge = displayLarge,
        displayMedium = displayMedium,
        displaySmall = displaySmall,
        headlineLarge = headlineLarge,
        headlineMedium = headlineMedium,
        headlineSmall = headlineSmall,
        titleLarge = titleLarge,
        titleMedium = titleMedium,
        titleSmall = titleSmall,
        bodyLarge = bodyLarge,
        bodyMedium = bodyMedium,
        bodySmall = bodySmall,
        labelLarge = labelLarge,
        labelMedium = labelMedium,
        labelSmall = labelSmall,
        button = button,
    )

    @Suppress("ComplexMethod")
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Typography

        return displayLarge == other.displayLarge &&
            displayMedium == other.displayMedium &&
            displaySmall == other.displaySmall &&
            headlineLarge == other.headlineLarge &&
            headlineMedium == other.headlineMedium &&
            headlineSmall == other.headlineSmall &&
            titleLarge == other.titleLarge &&
            titleMedium == other.titleMedium &&
            titleSmall == other.titleSmall &&
            bodyLarge == other.bodyLarge &&
            bodyMedium == other.bodyMedium &&
            bodySmall == other.bodySmall &&
            labelLarge == other.labelLarge &&
            labelMedium == other.labelMedium &&
            labelSmall == other.labelSmall &&
            button == other.button
    }

    @Suppress("ComplexMethod")
    override fun hashCode(): Int {
        var result = displayLarge.hashCode()
        result = 31 * result + displayMedium.hashCode()
        result = 31 * result + displaySmall.hashCode()
        result = 31 * result + headlineLarge.hashCode()
        result = 31 * result + headlineMedium.hashCode()
        result = 31 * result + headlineSmall.hashCode()
        result = 31 * result + titleLarge.hashCode()
        result = 31 * result + titleMedium.hashCode()
        result = 31 * result + titleSmall.hashCode()
        result = 31 * result + bodyLarge.hashCode()
        result = 31 * result + bodyMedium.hashCode()
        result = 31 * result + bodySmall.hashCode()
        result = 31 * result + labelLarge.hashCode()
        result = 31 * result + labelMedium.hashCode()
        result = 31 * result + labelSmall.hashCode()
        result = 31 * result + button.hashCode()
        return result
    }

    override fun toString() =
        "Typography(displayLarge=$displayLarge, displayMedium=$displayMedium, displaySmall=$displaySmall, " +
            "headlineLarge=$headlineLarge, headlineMedium=$headlineMedium, headlineSmall=$headlineSmall, " +
            "titleLarge=$titleLarge, titleMedium=$titleMedium, titleSmall=$titleSmall, " +
            "bodyLarge=$bodyLarge, bodyMedium=$bodyMedium, bodySmall=$bodySmall, " +
            "labelLarge=$labelLarge, labelMedium=$labelMedium, labelSmall=$labelSmall, button=$button)"
}

private fun TextStyle.withDefaultFont(default: FontFamily): TextStyle {
    return if (fontFamily != null) this else copy(fontFamily = default)
}

@Composable
fun ProvideTypography(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalTypography provides Typography(),
        content = content
    )
}

internal val LocalTypography = staticCompositionLocalOf<Typography> {
    error("No typography provided")
}
