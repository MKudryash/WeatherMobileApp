package com.example.weatherappmobile.Data.api

import com.example.weathermobileapp.Data.api.WeatherApi
import com.example.weathermobileapp.Domain.models.WeatherModel
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * Реализация интерфейса WeatherApi
 * */
class WeatherApiService  @Inject constructor(private val retrofit: Retrofit) : WeatherApi {

    override suspend fun getCurrentWeather(city: String, days: Int?, tp: Int?): WeatherModel  =
        retrofit.create(WeatherApi::class.java).getCurrentWeather(city,days,tp)
}