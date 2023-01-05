package com.dor.compose.playground.composedestinationslibrary.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
internal fun SimpleScreen(){
    Text(text = "Simple screen")
}