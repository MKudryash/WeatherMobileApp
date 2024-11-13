package com.example.weathermobileapp.Domain.useCase

import com.example.weathermobileapp.Data.repository.InternetRepository

/**
 * Реализация IsInternetAvailableUseCase, которая делегирует работу репозиторию
 * */
class IsInternetAvailableUseCaseImpl(private val internetRepository: InternetRepository) : IsInternetAvailableUseCase {
    override suspend fun execute(): Boolean {
        return internetRepository.isInternetAvailable()
    }
}