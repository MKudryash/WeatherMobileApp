package com.example.weathermobileapp.Presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathermobileapp.Domain.models.Forecastday
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyWeatherCard(weather: Forecastday) {
    // Определяем формат даты и времени
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    // Парсим строку в LocalDateTime
    val date = LocalDate.parse(weather.date, formatter)

    // Получаем день недели
    val dayOfWeek: DayOfWeek = date.dayOfWeek

    // Получаем название дня недели
    val dayOfWeekName =
        dayOfWeek.name // Это вернет название дня в верхнем регистре (например, MONDAY)
    val dayOfWeekNameFormatted = dayOfWeekName.lowercase().replaceFirstChar {
        it.uppercase()
    }  // Форматируем название
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(text = dayOfWeekNameFormatted, fontSize = 18.sp, modifier = Modifier.weight(1f))
            Text(
                text = "${weather.day.maxtemp_c}° / ${weather.day.mintemp_c}°",
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            )
            {
                ConditionIcon(weather.day.condition.icon, 30.dp, 30.dp)
                Text(text = weather.day.condition.text, fontSize = 14.sp, textAlign = TextAlign.Center, lineHeight = 14.sp)
            }
        }
    }
}