package com.dor.compose.playground.composedestinationslibrary.screens.navigationwithargs

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ramcosta.composedestinations.annotation.Destination
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dor.compose.playground.composedestinationslibrary.models.User

@Destination(navArgsDelegate = User::class)
@Composable
internal fun NavigationWithArgsToViewModelScreen(
    viewModel: NavigationWithArgsViewModel = viewModel(),
) {
    val user by viewModel.user.collectAsState()
    Text(text = "${user.name} ${user.surname}")
}