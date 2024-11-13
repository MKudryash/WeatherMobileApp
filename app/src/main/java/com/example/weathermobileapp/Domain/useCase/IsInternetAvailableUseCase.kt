package com.example.weathermobileapp.Domain.useCase


/**
 * Интерфейс, который определяет функциональность use-case для проверки доступности интернета.
 * */
interface IsInternetAvailableUseCase {
    suspend fun execute(): Boolean
}