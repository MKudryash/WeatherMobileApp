package com.example.weathermobileapp.Domain.useCase

import com.example.weathermobileapp.Data.interfaceData.WeatherRepositoryInterface
import com.example.weathermobileapp.Domain.models.WeatherModel
import javax.inject.Inject

/**
 * Получение текущей погоды для определенного города.
 * */
class GetCurrentWeatherUseCase @Inject constructor(private val weatherRepository: WeatherRepositoryInterface) {
    suspend fun execute(city: String, days: Int?, tp: Int?): WeatherModel {
        return weatherRepository.getCurrentWeather(city ,days, tp)
    }
}