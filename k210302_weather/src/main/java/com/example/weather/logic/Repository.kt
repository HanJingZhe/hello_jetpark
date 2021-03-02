package com.example.weather.logic

import androidx.lifecycle.liveData
import com.example.weather.logic.model.Place
import com.example.weather.logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers

/**
 * 仓库层同一封装入口
 */
object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = WeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }


}