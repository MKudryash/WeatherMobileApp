package com.example.weathermobileapp.Data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.weathermobileapp.Data.repository.InternetRepository

/**
 * Реализация интерфейса InternetRepository, предоставляющая конкретную логику проверки.
 * */
class InternetRepositoryImpl(private val context: Context) : InternetRepository {
    override suspend fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        return network != null && connectivityManager.getNetworkCapabilities(network)?.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}