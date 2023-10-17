package com.dor.compose.playground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dor.compose.playground.composables.theme.MainTheme
import com.dor.compose.playground.composedestinationslibrary.screens.NavigationLibraryEntryScreen
import com.dor.compose.playground.screens.AutoResizedTextScreen
import com.dor.compose.playground.screens.CustomButtonsScreen
import com.dor.compose.playground.screens.CustomPullRefreshScreen
import com.dor.compose.playground.screens.DragAndDropListScreen
import com.dor.compose.playground.screens.MainScreen
import com.dor.compose.playground.screens.PullRefreshScreen
import com.dor.compose.playground.screens.CollapsingToolbarScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("customButton") { CustomButtonsScreen() }
                    composable("pulltorefresh") { PullRefreshScreen() }
                    composable("custompulltorefresh") { CustomPullRefreshScreen() }
                    composable("navigationLibrary") { NavigationLibraryEntryScreen() }
                    composable("autoresizedtext") { AutoResizedTextScreen() }
                    composable("collapsingtoolbar") { CollapsingToolbarScreen() }
                    composable("draganddroplist") { DragAndDropListScreen() }
                }
            }
        }
    }
}
