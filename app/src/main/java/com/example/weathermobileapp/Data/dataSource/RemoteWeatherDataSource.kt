package com.example.weathermobileapp.Data.dataSource

import com.example.weatherappmobile.Data.api.WeatherApiService
import com.example.weathermobileapp.Data.interfaceData.CurrentWeatherDataSource
import com.example.weathermobileapp.Domain.models.WeatherModel
import javax.inject.Inject

/**
 * Реализация интерфейсf CurrentWeatherDataSource с помощью WeatherApiService.
 * */
class RemoteWeatherDataSource @Inject constructor(private val weatherApiService: WeatherApiService) :
    CurrentWeatherDataSource {

    override suspend fun getCurrentWeather(city: String, days: Int?, tp: Int?): WeatherModel {
        return weatherApiService.getCurrentWeather(city, days, tp)
    }
}