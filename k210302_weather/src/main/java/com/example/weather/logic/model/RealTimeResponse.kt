package com.example.weather.logic.model

import com.google.gson.annotations.SerializedName

data class RealTimeResponse(
    val api_status: String,
    val api_version: String,
    val lang: String,
    val location: List<Double>,
    val result: Result,
    val server_time: String,
    val status: String,
    val timezone: String,
    val tzshift: String,
    val unit: String
) {

    data class Result(
        val primary: String,
        val realtime: Realtime
    )

    data class Realtime(
        @SerializedName("air_quality")
        val airQuality: AirQuality,
        val apparent_temperature: Double,
        val cloudrate: Double,
        val dswrf: Double,
        val humidity: Double,
        val life_index: LifeIndex,
        val precipitation: Precipitation,
        val pressure: Double,
        val skycon: String,
        val status: String,
        val temperature: Double,
        val visibility: Double,
        val wind: Wind
    )

    data class AirQuality(
        val aqi: Aqi,
        val co: String,
        val description: Description,
        val no2: String,
        val o3: String,
        val pm10: String,
        val pm25: String,
        val so2: String
    )

    data class LifeIndex(
        val comfort: Comfort,
        val ultraviolet: Ultraviolet
    )

    data class Precipitation(
        val local: Local,
        val nearest: Nearest
    )

    data class Wind(
        val direction: Double,
        val speed: Double
    )

    data class Aqi(
        val chn: String,
        val usa: String
    )

    data class Description(
        val chn: String,
        val usa: String
    )

    data class Comfort(
        val desc: String,
        val index: String
    )

    data class Ultraviolet(
        val desc: String,
        val index: Double
    )

    data class Local(
        val datasource: String,
        val Stringensity: Double,
        val status: String
    )

    data class Nearest(
        val distance: Double,
        val Stringensity: Double,
        val status: String
    )
}