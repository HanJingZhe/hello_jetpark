package com.example.weather.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.weather.WLog
import com.example.weather.logic.model.Weather
import com.example.weather.logic.network.WeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * 仓库层同一封装入口
 */
object Repository {

    val TAG = this.javaClass.simpleName

    //liveData自动构建并返回一个LiveData对象,然后在代码块中提供一个挂起函数的上下文
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = WeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun getRealtimeWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            val realtimeWeather = WeatherNetwork.getRealtimeWeather(lng, lat)
            if (realtimeWeather.status == "ok") {
                Log.e(TAG, "success")
                Result.success(realtimeWeather)
            } else {
                Log.e(TAG, "no ok")
                Result.failure(RuntimeException("single request fail"))
            }
        } catch (e: Exception) {
            Log.e(TAG,"e= ${e.toString()}")
            Result.failure(e)
        }
        emit(result)
    }

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        //coroutineScope:是一个挂起函数,会继承外部的协程作用域并创建一个子作用域
        //可以保证其作用域内的所有代码和子协程在全部执行完之前,会一直阻塞当前协程
        WLog.d(TAG, "lng=$lng,lat=$lat")
        coroutineScope {
            val deferredRealTime = async {
                WeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                WeatherNetwork.getDailyWeather(lng, lat)
            }
            val realTimeResponse = deferredRealTime.await()
            val dailyResponse = deferredDaily.await()
            if (realTimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realTimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realTimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }


    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }


    fun searchPlaces2(query: String) = liveData(Dispatchers.IO) {
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

    fun refreshWeather2(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            //coroutineScope:是一个挂起函数,会继承外部的协程作用域并创建一个子作用域
            //可以保证其作用域内的所有代码和子协程在全部执行完之前,会一直阻塞当前协程
            coroutineScope {
                val deferredRealTime = async {
                    WeatherNetwork.getRealtimeWeather(lng, lat)
                }

                val deferredDaily = async {
                    WeatherNetwork.getDailyWeather(lng, lat)
                }
                val realTimeResponse = deferredRealTime.await()
                val dailyResponse = deferredDaily.await()
                if (realTimeResponse.status == "ok" && dailyResponse.status == "ok") {
                    val weather =
                        Weather(realTimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                } else {
                    Result.failure(
                        RuntimeException(
                            "realtime response status is ${realTimeResponse.status}" +
                                    "daily response status is ${dailyResponse.status}"
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Result.failure<Weather>(e)
        }
        emit(result)
    }

}