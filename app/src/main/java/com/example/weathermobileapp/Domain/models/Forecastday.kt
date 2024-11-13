package com.example.weathermobileapp.Domain.models

import com.example.weathermobileapp.Domain.models.Astro


data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)