package com.example.weathermobileapp.Presentation.currentweather

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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState.asStateFlow()

    private val _weatherStateSearch = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherStateSearch: StateFlow<WeatherState> = _weatherStateSearch.asStateFlow()

    private val _selectedCity = MutableStateFlow("London")
    val selectedCity: StateFlow<String> = _selectedCity.asStateFlow()
    private lateinit var locationManager: LocationManager
    private val location = mutableStateOf<Location?>(null)

    private var cachedWeatherData: WeatherModel? = null


    init {
        fetchWeatherData(selectedCity.value)
    }


    private fun fetchWeatherData(city: String) {
        cachedWeatherData?.let {
            _weatherState.value = WeatherState.Success(it)
        }
        viewModelScope.launch {
            _weatherState.value = WeatherState.Loading
            try {
                val currentWeather = getCurrentWeatherUseCase.execute(city,7,24)
                cachedWeatherData = currentWeather
                _weatherState.value = WeatherState.Success(currentWeather)
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message ?: "Ошибка получения данных")
            }
        }
    }
}