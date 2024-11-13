package com.example.weathermobileapp.Presentation

import android.content.Context
import com.example.weatherappmobile.Data.api.WeatherApiService
import com.example.weathermobileapp.BuildConfig
import com.example.weathermobileapp.Data.InternetRepositoryImpl
import com.example.weathermobileapp.Data.api.WeatherApiInterceptor
import com.example.weathermobileapp.Data.dataSource.RemoteWeatherDataSource
import com.example.weathermobileapp.Data.interfaceData.WeatherRepositoryInterface
import com.example.weathermobileapp.Data.repository.InternetRepository
import com.example.weathermobileapp.Data.repository.WeatherRepository
import com.example.weathermobileapp.Domain.useCase.GetCurrentWeatherUseCase
import com.example.weathermobileapp.Domain.useCase.IsInternetAvailableUseCase
import com.example.weathermobileapp.Domain.useCase.IsInternetAvailableUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    /**
     * Создает объект InternetRepositoryImpl, который отвечает за получений сведений о сотоянии интрнета
     * */
    @Provides
    @Singleton
    fun provideInternetRepository(@ApplicationContext context: Context): InternetRepository {
        return  InternetRepositoryImpl(context)
    }
    /**
     * Создает объект IsInternetAvailableUseCaseImpl, который представляет собой use-case для получения состояния интернета
     * */

    @Provides
    @Singleton
    fun provideIsInternetAvailableUseCase(internetRepository: InternetRepository): IsInternetAvailableUseCase {
        return  IsInternetAvailableUseCaseImpl(internetRepository)
    }

}