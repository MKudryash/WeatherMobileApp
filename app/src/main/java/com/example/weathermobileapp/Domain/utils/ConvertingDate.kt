package com.example.weathermobileapp.Domain.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object ConvertingDate {
    @RequiresApi(Build.VERSION_CODES.O)
    /**
     * Получение времени из даты
     * */
    fun ExtractingTimeFromDate(dataTime:String): LocalTime? {
        // Определяем формат даты и времени
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        // Парсим строку в LocalDateTime
        val dateTime = LocalDateTime.parse(dataTime, dateTimeFormatter)

        // Извлекаем время
        return dateTime.toLocalTime()
    }
    /**
     * Получение времени из строки
     * */
    @RequiresApi(Build.VERSION_CODES.O)
    fun ExtractingTimeFromStringDate(time:String): LocalTime? {
        // Определяем формат даты и времени
        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")
        return LocalTime.parse(time, timeFormatter)
    }
}