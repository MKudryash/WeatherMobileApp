package com.example.weathermobileapp.Presentation.currentweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.weathermobileapp.Domain.WeatherState
import com.example.weathermobileapp.Presentation.components.CurrentWeatherSection
import com.example.weathermobileapp.Presentation.components.DailyWeatherColumn
import com.example.weathermobileapp.R


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel(), navController: NavHostController) {
    val weatherState by viewModel.weatherState.collectAsState()

    val context = LocalContext.current

    // Получить геолокацию в viewModel
    LaunchedEffect(key1 = Unit) {
        viewModel.getLocation(context)
    }
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {

                when (weatherState) {
                    is WeatherState.Loading -> {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 100.dp, horizontal = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is WeatherState.Success -> {
                        CurrentWeatherSection(
                            (weatherState as WeatherState.Success).weatherData,
                            navController, true
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        DailyWeatherColumn((weatherState as WeatherState.Success).weatherData)//Список из прогноза
                    }

                    is WeatherState.Error -> Text((weatherState as WeatherState.Error).message)
                }

            }
        }
    }
}
