package com.example.weathermobileapp.Presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathermobileapp.Domain.models.Hour
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimeWeatherCard(weather: Hour, time: LocalTime) {


        Card(
            modifier = Modifier
                .width(70.dp)
                .height(90.dp)
                .padding(horizontal = 3.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = time.toString(),
                    fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )
                ConditionIcon(weather.condition.icon, 30.dp, 30.dp)
                Text(
                    text = "${weather.temp_c}°",
                    fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
