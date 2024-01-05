package com.dor.compose.playground.composedestinationslibrary.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dor.compose.playground.composedestinationslibrary.models.User
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
internal fun NavigationWithArgsScreen(
    user: User,
) {
    Text(text = "${user.name} ${user.surname}")
}