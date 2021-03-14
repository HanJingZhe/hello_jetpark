package com.example.weather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.example.weather.WeatherApplication
import com.example.weather.logic.model.PlaceResponse
import com.google.gson.Gson

object PlaceDao {

    fun savePlace(place: PlaceResponse.Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getPlace(): PlaceResponse.Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, PlaceResponse.Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() =
        WeatherApplication.mAppContext.getSharedPreferences("weather", Context.MODE_PRIVATE)

}