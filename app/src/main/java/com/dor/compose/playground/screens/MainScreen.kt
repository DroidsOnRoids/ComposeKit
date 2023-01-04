package com.dor.compose.playground.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Button(onClick = { navController.navigate("customButton") }) {
                Text(text = "Custom Buttons")
            }
            Button(onClick = { navController.navigate("pulltorefresh") }) {
                Text(text = "[Material] Pull to refresh")
            }
            Button(
                onClick = { navController.navigate("custompulltorefresh") }
            ) {
                Text(text = "[Custom] Pull to refresh")
            }
            Button(
                onClick = { navController.navigate("autoresizedtext") }
            ) {
                Text(text = "Auto resized text")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}