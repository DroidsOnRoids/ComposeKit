package com.dor.compose.playground.composedestinationslibrary.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.dor.compose.playground.composedestinationslibrary.models.User
import com.dor.compose.playground.composedestinationslibrary.navgraphs.ComposeDestinationsLibraryNavGraph
import com.dor.compose.playground.composedestinationslibrary.screens.destinations.NavigationWithArgsScreenDestination
import com.dor.compose.playground.composedestinationslibrary.screens.destinations.NavigationWithArgsToViewModelScreenDestination
import com.dor.compose.playground.composedestinationslibrary.screens.destinations.NavigationWithResultScreenDestination
import com.dor.compose.playground.composedestinationslibrary.screens.destinations.SimpleScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient

@ComposeDestinationsLibraryNavGraph(start = true)
@Destination
@Composable
internal fun NavigationLibraryMainScreen(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<NavigationWithResultScreenDestination, String>,
) {
    val context = LocalContext.current
    resultRecipient.onNavResult { result ->
        when (result) {
            is NavResult.Canceled -> {}
            is NavResult.Value -> {
                Toast.makeText(context, result.value, Toast.LENGTH_LONG).show()
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp, alignment = Alignment.CenterVertically)
    ) {
        NavButton(text = "Simple navigation") {
            navigator.navigate(SimpleScreenDestination())
        }
        NavButton(text = "Navigation with args") {
            val user = User("John", "Doe")
            navigator.navigate(NavigationWithArgsScreenDestination(user))
        }
        NavButton(text = "Navigation with args to viewModel") {
            val user = User("John2", "Doe2")
            navigator.navigate(NavigationWithArgsToViewModelScreenDestination(user))
        }
        NavButton(text = "Navigation with result") {
            navigator.navigate(NavigationWithResultScreenDestination())
        }
    }
}

@Composable
private fun NavButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        shape = RoundedCornerShape(10.dp),
        onClick = { onClick() }
    ) {
        Text(text = text, color = Color.White)
    }
}