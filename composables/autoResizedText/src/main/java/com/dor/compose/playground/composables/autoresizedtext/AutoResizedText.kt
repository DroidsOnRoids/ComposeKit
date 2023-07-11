package com.dor.compose.playground.composables.autoresizedtext

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
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

@Composable
fun AutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
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
