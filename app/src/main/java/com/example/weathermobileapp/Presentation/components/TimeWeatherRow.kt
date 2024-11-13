package com.example.weathermobileapp.Presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.weathermobileapp.Domain.models.WeatherModel
import com.example.weathermobileapp.Domain.utils.ConvertingDate.ExtractingTimeFromDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeWeatherRow(weatherData: WeatherModel) {
    val timeLocal = ExtractingTimeFromDate(weatherData.location.localtime)
    LazyRow(
        modifier = Modifier
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(weatherData.forecast.forecastday[0].hour){it ->
            val time =  ExtractingTimeFromDate(it.time)
            if (time!!.isAfter(timeLocal)) {
                TimeWeatherCard(it, time)
            }
        }
    }
}