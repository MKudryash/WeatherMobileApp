package com.example.weathermobileapp.Presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.TireRepair
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathermobileapp.Domain.models.WeatherModel

@Composable
fun OtherData(weatherData: WeatherModel) {
    Row {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Air,
                    contentDescription = "Wind",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Blue
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Wind: ${weatherData.current.wind_mph} m/s",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.WaterDrop,
                    contentDescription = "Humidity",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Blue
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Humidity: ${weatherData.current.humidity}%",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.TireRepair,
                    contentDescription = "Humidity",
                    modifier = Modifier.size(40.dp),
                    tint = Color.LightGray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Pressure: ${weatherData.current.pressure_mb} mB",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            // Sunrise
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.WbSunny,
                    contentDescription = "Sunrise",
                    modifier = Modifier.size(40.dp),
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sunrise: ${weatherData.forecast.forecastday[0].astro.sunrise}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Sunset
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Nightlight,
                    contentDescription = "Sunset",
                    modifier = Modifier.size(40.dp),
                    tint = Color(0xFFFF8C00)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sunset: ${weatherData.forecast.forecastday[0].astro.sunset}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

        }
    }
}