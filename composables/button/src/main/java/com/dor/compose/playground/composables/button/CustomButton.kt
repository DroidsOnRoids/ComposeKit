package com.dor.compose.playground.composables.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.dor.compose.playground.composables.utils.disabled

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    iconPainter: Painter? = null,
    buttonType: ButtonType = ButtonType.Filled,
    enabled: Boolean = true,
) {
    val newModifier = modifier
        .fillMaxWidth()
        .requiredHeight(height = 56.dp)

    if (buttonType.isOutlined) {
        val disabledContentColor = buttonType.contentColor.disabled()
        OutlinedButton(
            onClick = onClick,
            modifier = newModifier,
            enabled = enabled,
            border = outlinedButtonBorder(enabled, buttonType.contentColor, disabledContentColor),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonType.backgroundColor,
                contentColor = buttonType.contentColor,
                disabledBackgroundColor = buttonType.backgroundColor,
                disabledContentColor = disabledContentColor
            )
        ) {
            iconPainter?.let { ButtonIcon(it) }
            ButtonText(title)
        }
    } else {
        Button(
            onClick = onClick,
            modifier = newModifier,
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = buttonType.backgroundColor,
                contentColor = buttonType.contentColor,
                disabledBackgroundColor = buttonType.backgroundColor.disabled(),
                disabledContentColor = buttonType.contentColor.disabled()
            )
        ) {
            iconPainter?.let { ButtonIcon(it) }
            ButtonText(title)
        }
    }
}

@Composable
private fun ButtonText(title: String) {
    Text(
        text = title,
    )
}

@Composable
private fun ButtonIcon(iconPainter: Painter) {
    Icon(
        painter = iconPainter,
        contentDescription = null,
        tint = Color.Black,
        modifier = Modifier.padding(end = 8.dp)
    )
}

@Composable
internal fun outlinedButtonBorder(
    isEnabled: Boolean,
    color: Color = Color.Black,
    disabledColor: Color = color.disabled(),
) = BorderStroke(
    ButtonDefaults.OutlinedBorderSize,
    if (isEnabled) color else disabledColor,
)

@Stable
sealed class ButtonType(
    internal val isOutlined: Boolean,
    private val isFilled: Boolean,
) {

    val backgroundColor @Composable get() = if (isFilled) Color.Black else Color.White
    val contentColor @Composable get() = if (isFilled) Color.White else Color.Black

    @Immutable object Outlined : ButtonType(isOutlined = true, isFilled = false)
    @Immutable object TextElevated : ButtonType(isOutlined = false, isFilled = false)
    @Immutable object Filled : ButtonType(isOutlined = false, isFilled = true)
}