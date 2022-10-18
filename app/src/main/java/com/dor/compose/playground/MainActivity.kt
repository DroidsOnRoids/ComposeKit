package com.dor.compose.playground

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dor.compose.playground.composables.theme.MainTheme
import com.dor.compose.playground.screens.CustomButtonsScreen
import com.dor.compose.playground.screens.MainScreen
import com.dor.compose.playground.screens.navigationTutorial.FirstScreen
import com.dor.compose.playground.screens.navigationTutorial.SecondScreen
import com.dor.compose.playground.screens.navigationTutorial.ThirdScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "main") {
                    composable("main") { MainScreen(navController) }
                    composable("customButton") { CustomButtonsScreen() }
                    composable(route = "first", content = { FirstScreen(navController) })
                    composable(
                        route = "second?login={login}",
                        arguments = listOf(
                            navArgument("login") { type = NavType.StringType }
                        ),
                    ) { entry ->
                        val login = entry.arguments?.getString("login").orEmpty()
                        SecondScreen(login, navigateWithPassword = { password ->
                            navController.navigate(
                                route = "third?login=$login&password=$password"
                            )
                        })
                    }
                    composable(
                        route = "third?login={login}&password={password}",
                        arguments = listOf(
                            navArgument("login") { type = NavType.StringType },
                            navArgument("password") { type = NavType.StringType },
                        )
                    ) { entry ->
                        val login = requireNotNull(entry.arguments).getString("login", "")
                        val password = requireNotNull(entry.arguments).getString("password", "")
                        ThirdScreen(login, password)
                    }
                }
            }
        }
    }
}