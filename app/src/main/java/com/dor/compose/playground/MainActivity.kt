package com.dor.compose.playground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dor.compose.playground.composables.theme.MainTheme
import com.dor.compose.playground.screens.AutoResizedTextScreen
import com.dor.compose.playground.screens.CustomPullRefreshScreen
import com.dor.compose.playground.screens.DragAndDropListScreen
import com.dor.compose.playground.screens.MainScreen
import com.dor.compose.playground.screens.PullRefreshScreen
import com.dor.compose.playground.screens.CollapsingToolbarScreen
import com.dor.compose.playground.screens.FoldableToolbarScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("pulltorefresh") { PullRefreshScreen() }
                    composable("custompulltorefresh") { CustomPullRefreshScreen() }
                    composable("autoresizedtext") { AutoResizedTextScreen() }
                    composable("collapsingtoolbar") { CollapsingToolbarScreen() }
                    composable("foldabletoolbar") { FoldableToolbarScreen() }
                    composable("draganddroplist") { DragAndDropListScreen() }
                }
            }
        }
    }
}
