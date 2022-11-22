package com.dor.compose.playground.composables.utils

import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Color.disabled(): Color = copy(alpha = ContentAlpha.disabled)