package com.example.weathermobileapp.Domain

import com.example.weathermobileapp.Domain.models.WeatherModel

/**
 * WeatherState:  Закрытый класс, который представляет состояние погоды.
 * * Loading:  Объект-состояние, которое означает, что данные о погоде еще загружаются.
 * * Success:  Данные-состояние, которое содержит  WeatherModel  с информацией о погоде, если запрос был успешным.
 * * Error:  Данные-состояние, которое содержит сообщение об ошибке, если запрос был неудачным.
 * */
sealed class WeatherState {
    data object Loading : WeatherState()
    data class Success(val weatherData: WeatherModel) : WeatherState()
    data class Error(val message: String) : WeatherState()
}