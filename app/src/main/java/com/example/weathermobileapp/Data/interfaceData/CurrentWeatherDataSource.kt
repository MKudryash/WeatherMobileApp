package com.example.weathermobileapp.Data.interfaceData

import com.example.weathermobileapp.Domain.models.WeatherModel

/**
  *Интерфейс для получения текущей погоды.
 */
interface CurrentWeatherDataSource {
    suspend fun getCurrentWeather(city: String, days: Int?, tp: Int?): WeatherModel
}