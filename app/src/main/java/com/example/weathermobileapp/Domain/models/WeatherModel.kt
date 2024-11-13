package com.example.weathermobileapp.Domain.models


/**
 * Данные о погоде
 * */
data class WeatherModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)