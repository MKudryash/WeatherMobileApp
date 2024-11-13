package com.example.weathermobileapp.Presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weathermobileapp.Domain.models.WeatherModel
import com.example.weathermobileapp.Domain.utils.ConvertingDate.ExtractingTimeFromDate
import com.example.weathermobileapp.Domain.utils.ConvertingDate.ExtractingTimeFromStringDate
import com.example.weathermobileapp.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentWeatherSection(
    weatherData: WeatherModel,
    navController: NavHostController,
    searching: Boolean
) {
    // Извлекаем время
    val time = ExtractingTimeFromDate (weatherData.location.localtime)

    // Парсим время восхода и заката
    val sunrise = ExtractingTimeFromStringDate(weatherData.forecast.forecastday[0].astro.sunrise)
    val sunset =  ExtractingTimeFromStringDate(weatherData.forecast.forecastday[0].astro.sunset)

    // Проверяем, попадает ли время в промежуток
    val background =
        if (time!!.isAfter(sunrise) && time.isBefore(sunset)) painterResource(id = R.drawable.day) else painterResource(
            id = R.drawable.night
        )

    Box()
    {
        if (searching) {
            Box(
                contentAlignment = Alignment.TopStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp, top = 5.dp)
                    .clickable {
                        navController.navigate("searchWeatherScreen")
                    }
            )
            {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "",
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
        Image(
            painter = background,
            contentDescription = "null",
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 1.dp),
            contentScale = ContentScale.Crop,
            alpha = 0.7f
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = weatherData.location.name, fontSize = 30.sp)


            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${weatherData.current.temp_c}°C",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            ConditionIcon(weatherData.current.condition.icon, 90.dp, 90.dp)
            Text(text = weatherData.current.condition.text, fontSize = 20.sp)

        }
    }
}