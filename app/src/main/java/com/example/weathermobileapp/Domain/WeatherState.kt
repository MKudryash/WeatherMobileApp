package com.example.weathermobileapp.Domain

import com.example.weathermobileapp.Domain.models.WeatherModel

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weatherData: WeatherModel) : WeatherState()
    data class Error(val message: String) : WeatherState()
}