package com.example.weathermobileapp.Data.interfaceData

import com.example.weathermobileapp.Domain.models.WeatherModel

interface WeatherRepositoryInterface {
    suspend fun getCurrentWeather(city: String, days: Int?, tp: Int?): WeatherModel
}