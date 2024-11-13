package com.example.weathermobileapp.Presentation.searchcityweather

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermobileapp.Domain.WeatherState
import com.example.weathermobileapp.Domain.models.WeatherModel
import com.example.weathermobileapp.Domain.useCase.GetCurrentWeatherUseCase
import com.example.weathermobileapp.Domain.useCase.IsInternetAvailableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val isInternetAvailableUseCase: IsInternetAvailableUseCase
) : ViewModel() {

    // Состояние ответа от api
    private val _weatherStateSearch = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherStateSearch: StateFlow<WeatherState> = _weatherStateSearch.asStateFlow()

    //Проверка интернета
    private val _internetStatus = MutableStateFlow(false)
    val internetStatus: StateFlow<Boolean> = _internetStatus

    //Кэширование данных
    private var cachedWeatherData: WeatherModel? = null

    init {
        viewModelScope.launch {
            _internetStatus.value = isInternetAvailableUseCase.execute()
        }
    }

    fun searchCity(city: String) {
        cachedWeatherData?.let {
            _weatherStateSearch.value = WeatherState.Success(it)
        }
        viewModelScope.launch {
            _weatherStateSearch.value = WeatherState.Loading
            try {
                val currentWeather = getCurrentWeatherUseCase.execute(city, 7, null)
                cachedWeatherData = currentWeather
                _weatherStateSearch.value = WeatherState.Success(currentWeather)
            } catch (e: Exception) {
                _weatherStateSearch.value =
                    WeatherState.Error(e.message ?: "Ошибка получения данных")
            }
        }
    }


}