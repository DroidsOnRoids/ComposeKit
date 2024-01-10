package pl.droidsonroids.composekit

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.droidsonroids.composekit.composables.theme.MainTheme
import pl.droidsonroids.composekit.screens.AutoResizedTextScreen
import pl.droidsonroids.composekit.screens.CustomPullRefreshScreen
import pl.droidsonroids.composekit.screens.DragAndDropListScreen
import pl.droidsonroids.composekit.screens.MainScreen
import pl.droidsonroids.composekit.screens.PullRefreshScreen
import pl.droidsonroids.composekit.screens.CollapsingToolbarScreen
import pl.droidsonroids.composekit.screens.FoldableToolbarScreen

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
