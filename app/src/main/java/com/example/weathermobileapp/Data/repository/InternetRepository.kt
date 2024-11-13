package com.example.weathermobileapp.Data.repository

/**
 *  Интерфейс, определяющий функциональность для проверки доступности интернета.
 * */
interface InternetRepository {
    suspend fun isInternetAvailable(): Boolean
}