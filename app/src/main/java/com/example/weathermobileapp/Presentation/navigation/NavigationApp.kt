package com.example.weathermobileapp.Presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weathermobileapp.Presentation.currentweather.WeatherScreen
import com.example.weathermobileapp.Presentation.searchcityweather.SearchWeatherScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            NavHost(navController = navController, startDestination = ConstantsNavigation.WeatherScreen.route) {
                composable(ConstantsNavigation.WeatherScreen.route) {
                    WeatherScreen(navController = navController)
                }
                composable(ConstantsNavigation.SearchWeatherScreen.route) {
                    SearchWeatherScreen(navController = navController)
                }
            }
        }
    }
}


