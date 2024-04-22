package com.application.handphone_app.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.application.handphone_app.data.viewmodel.HandphoneViewModel
import com.application.handphone_app.navigation.Screen
import com.application.handphone_app.ui.theme.HandphoneAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            HandphoneAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val handphoneViewModel = HandphoneViewModel(application)
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.AddHandphoneScreen.route) {
                            AddHandphoneScreen(handphoneViewModel = handphoneViewModel)
                        }
                        composable(route = Screen.AllHandphonesScreen.route) {
                            AllHandphonesScreen(
                                navController = navController,
                                handphoneViewModel = handphoneViewModel
                            )
                        }
                        composable(
                            route = Screen.HandphoneDetailsScreen.route + "/{handphone_id}",
                            arguments = listOf(
                                navArgument("handphone_id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                    nullable = false
                                }
                            )
                        ) {
                            val id = it.arguments?.getInt("handphone_id") ?: -1
                            HandphoneDetailScreen(id, handphoneViewModel = handphoneViewModel, navController)
                        }
                    }
                }
            }
        }
    }
}