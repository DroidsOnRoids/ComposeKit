package com.dor.compose.playground.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dor.compose.playground.composables.autoresizedtext.AutoResizedText

@Composable
fun AutoResizedTextScreen() {
    val text = "Hello World!, Hello World!, Hello World!"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.CenterVertically)
    ) {
        Text(text = text, style = TextStyle(fontSize = 26.sp), softWrap = false, color = Color.Black)
        AutoResizedText(text = text, style = TextStyle(fontSize = 26.sp, color = Color.Blue))
    }
}
