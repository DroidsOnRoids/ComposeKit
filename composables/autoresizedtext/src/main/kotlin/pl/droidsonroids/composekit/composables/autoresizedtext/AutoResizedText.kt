package pl.droidsonroids.composekit.composables.autoresizedtext

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Renders auto-resized text, adjusting its font size to fit within a specified minimum size.
 *
 * @param modifier The modifier to apply to this composable.
 * @param text The text to be displayed.
 * @param style The style to be applied to the text. Defaults to the current [LocalTextStyle].
 * @param minFontSize The minimum font size that the text should have. Defaults to `11.sp`.
 */
@Composable
fun AutoResizedText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    minFontSize: TextUnit = 11.sp,
) {
    var resizedTextStyle by remember {
        mutableStateOf(if (style.fontSize >= minFontSize) style else style.copy(fontSize = minFontSize))
    }
    var shouldDraw by remember { mutableStateOf(false) }

    Text(
        text = text,
        modifier = modifier.drawWithContent {
            if (shouldDraw) drawContent()
        },
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                val resizedFontSize = resizedTextStyle.fontSize * 0.95
                resizedTextStyle =
                    resizedTextStyle.copy(
                        fontSize = resizedFontSize.takeIf {
                            it >= minFontSize
                        } ?: minFontSize.also { shouldDraw = true }
                    )
            } else {
                shouldDraw = true
            }
        }
    )
}
