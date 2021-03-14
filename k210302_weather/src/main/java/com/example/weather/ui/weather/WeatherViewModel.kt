package com.example.weather.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.weather.logic.Repository
import com.example.weather.logic.model.PlaceResponse
import com.example.weather.logic.model.Weather

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<PlaceResponse.Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    val weatherLiveData: LiveData<Result<Weather>> = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
//        Repository.refreshWeather2(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = PlaceResponse.Location(lng, lat)
//        Repository.getRealtimeWeather(lng, lat)
//        Repository.refreshWeather3(lng, lat)
    }


}