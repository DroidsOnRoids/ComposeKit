package com.dor.compose.playground.composables.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
@Suppress("LongParameterList")
class Typography private constructor(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val paragraph: TextStyle,
    val paragraphSecondary: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,
    val button: TextStyle,
) {

    @Suppress("LongParameterList")
    constructor(
        defaultFontFamily: FontFamily = FontFamily.Default,
        h1: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            letterSpacing = (-0.02).em,
        ),
        h2: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
            letterSpacing = (-0.02).em,
        ),
        h3: TextStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        ),
        h4: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
        ),
        h5: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        ),
        subtitle1: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
        ),
        subtitle2: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
        ),
        paragraph: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
        ),
        paragraphSecondary: TextStyle = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            letterSpacing = 0.04.em,
        ),
        caption1: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            letterSpacing = 0.04.em,
        ),
        caption2: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            letterSpacing = 0.04.em,
        ),
        button: TextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            letterSpacing = 0.sp,
        ),
    ) : this(
        h1 = h1.withDefaultFontFamily(defaultFontFamily),
        h2 = h2.withDefaultFontFamily(defaultFontFamily),
        h3 = h3.withDefaultFontFamily(defaultFontFamily),
        h4 = h4.withDefaultFontFamily(defaultFontFamily),
        h5 = h5.withDefaultFontFamily(defaultFontFamily),
        subtitle1 = subtitle1.withDefaultFontFamily(defaultFontFamily),
        subtitle2 = subtitle2.withDefaultFontFamily(defaultFontFamily),
        paragraph = paragraph.withDefaultFontFamily(defaultFontFamily),
        paragraphSecondary = paragraphSecondary.withDefaultFontFamily(defaultFontFamily),
        button = button.withDefaultFontFamily(defaultFontFamily),
        caption1 = caption1.withDefaultFontFamily(defaultFontFamily),
        caption2 = caption2.withDefaultFontFamily(defaultFontFamily),
    )

    @Suppress("LongParameterList")
    fun copy(
        h1: TextStyle = this.h1,
        h2: TextStyle = this.h2,
        h3: TextStyle = this.h3,
        h4: TextStyle = this.h4,
        h5: TextStyle = this.h5,
        subtitle1: TextStyle = this.subtitle1,
        subtitle2: TextStyle = this.subtitle2,
        paragraph: TextStyle = this.paragraph,
        paragraphSecondary: TextStyle = this.paragraphSecondary,
        button: TextStyle = this.button,
        caption1: TextStyle = this.caption1,
        caption2: TextStyle = this.caption2,
    ): Typography = Typography(
        h1 = h1,
        h2 = h2,
        h3 = h3,
        h4 = h4,
        h5 = h5,
        subtitle1 = subtitle1,
        subtitle2 = subtitle2,
        paragraph = paragraph,
        paragraphSecondary = paragraphSecondary,
        button = button,
        caption1 = caption1,
        caption2 = caption2,
    )

    @Suppress("ComplexMethod")
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Typography

        if (h1 != other.h1) return false
        if (h2 != other.h2) return false
        if (h3 != other.h3) return false
        if (h4 != other.h4) return false
        if (h5 != other.h5) return false
        if (subtitle1 != other.subtitle1) return false
        if (subtitle2 != other.subtitle2) return false
        if (paragraph != other.paragraph) return false
        if (paragraphSecondary != other.paragraphSecondary) return false
        if (caption1 != other.caption1) return false
        if (caption2 != other.caption2) return false
        if (button != other.button) return false

        return true
    }

    @Suppress("ComplexMethod")
    override fun hashCode(): Int {
        var result = h1.hashCode()
        result = 31 * result + h2.hashCode()
        result = 31 * result + h3.hashCode()
        result = 31 * result + h4.hashCode()
        result = 31 * result + h5.hashCode()
        result = 31 * result + subtitle1.hashCode()
        result = 31 * result + subtitle2.hashCode()
        result = 31 * result + paragraph.hashCode()
        result = 31 * result + paragraphSecondary.hashCode()
        result = 31 * result + caption1.hashCode()
        result = 31 * result + caption2.hashCode()
        result = 31 * result + button.hashCode()
        return result
    }

    override fun toString() =
        "Typography(h1=$h1, h2=$h2, h3=$h3, h4=$h4, h5=$h5, subtitle1=$subtitle1, " +
            "subtitle2=$subtitle2, paragraph=$paragraph, paragraphSecondary=$paragraphSecondary, " +
            "caption1=$caption1, caption2=$caption2, button=$button)"
}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle {
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
