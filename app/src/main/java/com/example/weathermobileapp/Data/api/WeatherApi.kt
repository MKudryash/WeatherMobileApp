package com.example.weathermobileapp.Data.api

import com.example.weathermobileapp.Domain.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Интерфейс для взаимодействия с API (Retrofit)
 * */
interface WeatherApi {

    //Запрос к api для получения прогноза, где q - город или координаты, days - на сколько дней выдать прогноз, tp - на сколько почасовую погоду выводить
    @GET("forecast.json?")
    suspend fun getCurrentWeather(
        @Query("q") city: String,
        @Query("days") days: Int?,
        @Query("tp") tp: Int?,
    ): WeatherModel
}