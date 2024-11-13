package com.example.weathermobileapp.Presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weathermobileapp.Domain.models.WeatherModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyWeatherColumn(weatherData: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        weatherData.forecast.forecastday.forEach { it ->
            DailyWeatherCard(it)
        }
    }
}