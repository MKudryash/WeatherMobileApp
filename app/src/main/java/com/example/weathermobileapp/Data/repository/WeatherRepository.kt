package com.example.weathermobileapp.Data.repository

import com.example.weatherappmobile.Data.api.WeatherApiService
import com.example.weathermobileapp.Data.interfaceData.WeatherRepositoryInterface
import com.example.weathermobileapp.Domain.models.WeatherModel
import javax.inject.Inject

/**
 * Класс, отвечающий за взаимодействие с WeatherRepositoryInterface и предоставление данных в Domain Layer.
 * */
class WeatherRepository @Inject constructor(private val weatherApiService: WeatherApiService) :
    WeatherRepositoryInterface {


    override suspend fun getCurrentWeather(city: String, days: Int?, tp: Int?): WeatherModel {
        val response = weatherApiService.getCurrentWeather(city, days, tp)
        return response
    }
}