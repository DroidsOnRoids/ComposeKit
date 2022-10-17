package com.dor.compose.playground.screens.navigationTutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dor.compose.playground.composables.theme.MainTheme

@Composable
fun SecondScreen() {
    var password by rememberSaveable {
        mutableStateOf("Password")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Show login here",
                color = Yellow,
                fontSize = MaterialTheme.typography.h3.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onBackground,
                backgroundColor = MaterialTheme.colors.background,
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
            ),
            modifier = Modifier.border(2.dp, MaterialTheme.colors.primary)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // TODO: Show if password not blank and old password != new password
        if (password.isNotBlank()) {
            Button(
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier.padding(5.dp),
                onClick = {
                    //TODO: Navigate to third screen with login and password values
                }
            ) {
                Text(
                    text = "Go to third screen",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(backgroundColor = White),
                modifier = Modifier.padding(5.dp),
                onClick = {
                    //TODO: Navigate back to first screen
                }
            ) {
                Text(
                    text = "Navigate back",
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Bold,
                        color = Black
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun SecondScreenPreview() {
    MainTheme {
        SecondScreen()
    }
}