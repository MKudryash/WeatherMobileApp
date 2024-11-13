package com.example.weathermobileapp.Presentation

import com.example.weatherappmobile.Data.api.WeatherApiService
import com.example.weathermobileapp.BuildConfig
import com.example.weathermobileapp.Data.api.WeatherApiInterceptor
import com.example.weathermobileapp.Data.dataSource.RemoteWeatherDataSource
import com.example.weathermobileapp.Data.interfaceData.WeatherRepositoryInterface
import com.example.weathermobileapp.Data.repository.WeatherRepository
import com.example.weathermobileapp.Domain.useCase.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Предоставления зависимостей
 * */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    /**
     * Создает объект OkHttpClient, который используется для отправки сетевых запросов. В нем есть interceptor, который добавляет API-ключ к заголовкам запросов.
     * */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(WeatherApiInterceptor(BuildConfig.API_KEY))
            .build()
    }


    /**
     *  Создает объект Retrofit, который используется для создания API-интерфейсов для работы с API-сервером.
     * */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_WEATHER)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Создает объект WeatherRepository, который отвечает за получение данных о погоде.
     * */
    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApiService: WeatherApiService): WeatherRepositoryInterface {
        return WeatherRepository(weatherApiService)
    }

    /**
     * Создает объект RemoteWeatherDataSource, который взаимодействует с API для получения данных о погоде.
     * */
    @Provides
    @Singleton
    fun provideRemoteWeatherDataSource(weatherApiService: WeatherApiService): RemoteWeatherDataSource {
        return RemoteWeatherDataSource(weatherApiService)
    }

    /**
     * Создает объект GetCurrentWeatherUseCase, который представляет собой use-case для получения текущей погоды
     * */
    @Provides
    @Singleton
    fun provideGetCurrentWeatherUseCase(weatherRepository: WeatherRepositoryInterface): GetCurrentWeatherUseCase {
        return GetCurrentWeatherUseCase(weatherRepository)
    }

}