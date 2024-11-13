package com.example.weathermobileapp.Domain.models

data class WeatherModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)