package com.dor.compose.playground.composedestinationslibrary.screens
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun NavigationLibraryEntryScreen() {
    DestinationsNavHost(navGraph = NavGraphs.composeDestinationsLibrary)
}
