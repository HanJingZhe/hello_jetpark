package com.example.weather.logic.model

data class Weather(val realTime: RealTimeResponse, val daily: DailyResponse.Daily)
