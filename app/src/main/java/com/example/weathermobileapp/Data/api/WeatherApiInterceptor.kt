package com.example.weathermobileapp.Data.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Перехватчик для добавления API-ключа, обработки ошибок
 * */
class WeatherApiInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("key", apiKey)
            .build()
        val request = originalRequest.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}