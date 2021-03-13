package com.example.weather.logic

import androidx.lifecycle.liveData
import com.example.weather.WLog
import com.example.weather.logic.model.Weather
import com.example.weather.logic.network.WeatherNetwork
import com.google.gson.Gson
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

    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        //coroutineScope:是一个挂起函数,会继承外部的协程作用域并创建一个子作用域
        //可以保证其作用域内的所有代码和子协程在全部执行完之前,会一直阻塞当前协程
        WLog.d(TAG, "lng=$lng,lat=$lat thread=${Thread.currentThread().name}")
        coroutineScope {
            val deferredRealTime = async {
                WeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                WeatherNetwork.getDailyWeather(lng, lat)
            }
            val realTimeResponse = deferredRealTime.await()
            val dailyResponse = deferredDaily.await()

            val resultJson =
                "{\"realtime\":{\"air_quality\":{\"aqi\":{\"chn\":\"171.0\",\"usa\":\"189.0\"},\"co\":\"1.1\",\"description\":{\"chn\":\"中度污染\",\"usa\":\"中度污染\"},\"no2\":\"35.0\",\"o3\":\"13.0\",\"pm10\":\"0.0\",\"pm25\":\"130.0\",\"so2\":\"3.0\"},\"apparent_temperature\":6.6,\"cloudrate\":0.3,\"dswrf\":0.0,\"humidity\":0.79,\"life_index\":{\"comfort\":{\"desc\":\"很冷\",\"index\":\"8\"},\"ultraviolet\":{\"desc\":\"无\",\"index\":0.0}},\"precipitation\":{\"local\":{\"datasource\":\"radar\",\"Stringensity\":0.0,\"status\":\"ok\"},\"nearest\":{\"distance\":10000.0,\"Stringensity\":0.0,\"status\":\"ok\"}},\"pressure\":101297.85,\"skycon\":\"MODERATE_HAZE\",\"status\":\"ok\",\"temperature\":8.0,\"visibility\":2.3,\"wind\":{\"direction\":212.0,\"speed\":3.96}},\"daily\":{\"air_quality\":{\"aqi\":[{\"avg\":{\"chn\":171.0,\"usa\":171.0},\"date\":\"2021-03-13T00:00+08:00\",\"max\":{\"chn\":\"204\",\"usa\":\"204\"},\"min\":{\"chn\":\"114\",\"usa\":\"114\"}},{\"avg\":{\"chn\":148.96,\"usa\":148.96},\"date\":\"2021-03-14T00:00+08:00\",\"max\":{\"chn\":\"164\",\"usa\":\"164\"},\"min\":{\"chn\":\"126\",\"usa\":\"126\"}},{\"avg\":{\"chn\":48.88,\"usa\":48.88},\"date\":\"2021-03-15T00:00+08:00\",\"max\":{\"chn\":\"131\",\"usa\":\"131\"},\"min\":{\"chn\":\"4\",\"usa\":\"4\"}},{\"avg\":{\"chn\":39.08,\"usa\":39.08},\"date\":\"2021-03-16T00:00+08:00\",\"max\":{\"chn\":\"72\",\"usa\":\"72\"},\"min\":{\"chn\":\"4\",\"usa\":\"4\"}},{\"avg\":{\"chn\":81.62,\"usa\":81.62},\"date\":\"2021-03-17T00:00+08:00\",\"max\":{\"chn\":\"95\",\"usa\":\"95\"},\"min\":{\"chn\":\"69\",\"usa\":\"69\"}}],\"pm25\":[{\"avg\":130.0,\"date\":\"2021-03-13T00:00+08:00\",\"max\":\"154\",\"min\":\"86\"},{\"avg\":113.75,\"date\":\"2021-03-14T00:00+08:00\",\"max\":\"125\",\"min\":\"96\"},{\"avg\":36.71,\"date\":\"2021-03-15T00:00+08:00\",\"max\":\"100\",\"min\":\"3\"},{\"avg\":27.88,\"date\":\"2021-03-16T00:00+08:00\",\"max\":\"53\",\"min\":\"3\"},{\"avg\":60.38,\"date\":\"2021-03-17T00:00+08:00\",\"max\":\"71\",\"min\":\"50\"}]},\"astro\":[{\"date\":\"2021-03-13T00:00+08:00\",\"sunrise\":{\"time\":\"06:28\"},\"sunset\":{\"time\":\"18:18\"}},{\"date\":\"2021-03-14T00:00+08:00\",\"sunrise\":{\"time\":\"06:27\"},\"sunset\":{\"time\":\"18:19\"}},{\"date\":\"2021-03-15T00:00+08:00\",\"sunrise\":{\"time\":\"06:25\"},\"sunset\":{\"time\":\"18:20\"}},{\"date\":\"2021-03-16T00:00+08:00\",\"sunrise\":{\"time\":\"06:24\"},\"sunset\":{\"time\":\"18:21\"}},{\"date\":\"2021-03-17T00:00+08:00\",\"sunrise\":{\"time\":\"06:22\"},\"sunset\":{\"time\":\"18:22\"}}],\"cloudrate\":[{\"avg\":0.3,\"date\":\"2021-03-13T00:00+08:00\",\"max\":1.0,\"min\":0.3},{\"avg\":0.84,\"date\":\"2021-03-14T00:00+08:00\",\"max\":1.0,\"min\":0.26},{\"avg\":0.22,\"date\":\"2021-03-15T00:00+08:00\",\"max\":0.77,\"min\":0.0},{\"avg\":0.15,\"date\":\"2021-03-16T00:00+08:00\",\"max\":0.6,\"min\":0.0},{\"avg\":0.39,\"date\":\"2021-03-17T00:00+08:00\",\"max\":1.0,\"min\":0.0}],\"dswrf\":[{\"avg\":0.0,\"date\":\"2021-03-13T00:00+08:00\",\"max\":585.2,\"min\":0.0},{\"avg\":105.9,\"date\":\"2021-03-14T00:00+08:00\",\"max\":389.1,\"min\":0.0},{\"avg\":248.1,\"date\":\"2021-03-15T00:00+08:00\",\"max\":673.8,\"min\":0.0},{\"avg\":244.5,\"date\":\"2021-03-16T00:00+08:00\",\"max\":650.0,\"min\":0.0},{\"avg\":242.9,\"date\":\"2021-03-17T00:00+08:00\",\"max\":641.0,\"min\":0.0}],\"humidity\":[{\"avg\":0.79,\"date\":\"2021-03-13T00:00+08:00\",\"max\":0.79,\"min\":0.39},{\"avg\":0.62,\"date\":\"2021-03-14T00:00+08:00\",\"max\":0.7,\"min\":0.49},{\"avg\":0.29,\"date\":\"2021-03-15T00:00+08:00\",\"max\":0.79,\"min\":0.07},{\"avg\":0.16,\"date\":\"2021-03-16T00:00+08:00\",\"max\":0.22,\"min\":0.12},{\"avg\":0.2,\"date\":\"2021-03-17T00:00+08:00\",\"max\":0.31,\"min\":0.11}],\"life_index\":{\"carWashing\":[{\"date\":\"2021-03-13T00:00+08:00\",\"desc\":\"较适宜\",\"index\":\"2\"},{\"date\":\"2021-03-14T00:00+08:00\",\"desc\":\"较适宜\",\"index\":\"2\"},{\"date\":\"2021-03-15T00:00+08:00\",\"desc\":\"适宜\",\"index\":\"1\"},{\"date\":\"2021-03-16T00:00+08:00\",\"desc\":\"较适宜\",\"index\":\"2\"},{\"date\":\"2021-03-17T00:00+08:00\",\"desc\":\"较适宜\",\"index\":\"2\"}],\"coldRisk\":[{\"date\":\"2021-03-13T00:00+08:00\",\"desc\":\"易发\",\"index\":\"3\"},{\"date\":\"2021-03-14T00:00+08:00\",\"desc\":\"易发\",\"index\":\"3\"},{\"date\":\"2021-03-15T00:00+08:00\",\"desc\":\"极易发\",\"index\":\"4\"},{\"date\":\"2021-03-16T00:00+08:00\",\"desc\":\"极易发\",\"index\":\"4\"},{\"date\":\"2021-03-17T00:00+08:00\",\"desc\":\"极易发\",\"index\":\"4\"}],\"comfort\":[{\"date\":\"2021-03-13T00:00+08:00\",\"desc\":\"很冷\",\"index\":\"8\"},{\"date\":\"2021-03-14T00:00+08:00\",\"desc\":\"很冷\",\"index\":\"8\"},{\"date\":\"2021-03-15T00:00+08:00\",\"desc\":\"寒冷\",\"index\":\"9\"},{\"date\":\"2021-03-16T00:00+08:00\",\"desc\":\"很冷\",\"index\":\"8\"},{\"date\":\"2021-03-17T00:00+08:00\",\"desc\":\"很冷\",\"index\":\"8\"}],\"dressing\":[{\"date\":\"2021-03-13T00:00+08:00\",\"desc\":\"冷\",\"index\":\"6\"},{\"date\":\"2021-03-14T00:00+08:00\",\"desc\":\"凉爽\",\"index\":\"5\"},{\"date\":\"2021-03-15T00:00+08:00\",\"desc\":\"冷\",\"index\":\"6\"},{\"date\":\"2021-03-16T00:00+08:00\",\"desc\":\"冷\",\"index\":\"6\"},{\"date\":\"2021-03-17T00:00+08:00\",\"desc\":\"冷\",\"index\":\"6\"}],\"ultraviolet\":[{\"date\":\"2021-03-13T00:00+08:00\",\"desc\":\"弱\",\"index\":\"2\"},{\"date\":\"2021-03-14T00:00+08:00\",\"desc\":\"最弱\",\"index\":\"1\"},{\"date\":\"2021-03-15T00:00+08:00\",\"desc\":\"强\",\"index\":\"4\"},{\"date\":\"2021-03-16T00:00+08:00\",\"desc\":\"强\",\"index\":\"4\"},{\"date\":\"2021-03-17T00:00+08:00\",\"desc\":\"中等\",\"index\":\"3\"}]},\"precipitation\":[{\"avg\":0.0,\"date\":\"2021-03-13T00:00+08:00\",\"max\":0.0,\"min\":0.0},{\"avg\":0.0,\"date\":\"2021-03-14T00:00+08:00\",\"max\":0.0,\"min\":0.0},{\"avg\":0.0,\"date\":\"2021-03-15T00:00+08:00\",\"max\":0.0,\"min\":0.0},{\"avg\":0.0,\"date\":\"2021-03-16T00:00+08:00\",\"max\":0.0,\"min\":0.0},{\"avg\":0.0,\"date\":\"2021-03-17T00:00+08:00\",\"max\":0.0,\"min\":0.0}],\"pressure\":[{\"avg\":101297.35,\"date\":\"2021-03-13T00:00+08:00\",\"max\":101948.71,\"min\":101246.98},{\"avg\":100821.56,\"date\":\"2021-03-14T00:00+08:00\",\"max\":101228.71,\"min\":100337.35},{\"avg\":101066.91,\"date\":\"2021-03-15T00:00+08:00\",\"max\":101918.04,\"min\":100206.98},{\"avg\":101693.33,\"date\":\"2021-03-16T00:00+08:00\",\"max\":102028.71,\"min\":101248.02},{\"avg\":101427.09,\"date\":\"2021-03-17T00:00+08:00\",\"max\":101646.98,\"min\":101166.98}],\"skycon\":[{\"date\":\"2021-03-13T00:00+08:00\",\"value\":\"MODERATE_HAZE\"},{\"date\":\"2021-03-14T00:00+08:00\",\"value\":\"LIGHT_HAZE\"},{\"date\":\"2021-03-15T00:00+08:00\",\"value\":\"PARTLY_CLOUDY_DAY\"},{\"date\":\"2021-03-16T00:00+08:00\",\"value\":\"CLEAR_DAY\"},{\"date\":\"2021-03-17T00:00+08:00\",\"value\":\"PARTLY_CLOUDY_DAY\"}],\"skycon_08h_20h\":[{\"date\":\"2021-03-13T00:00+08:00\",\"value\":\"LIGHT_HAZE\"},{\"date\":\"2021-03-14T00:00+08:00\",\"value\":\"LIGHT_HAZE\"},{\"date\":\"2021-03-15T00:00+08:00\",\"value\":\"PARTLY_CLOUDY_DAY\"},{\"date\":\"2021-03-16T00:00+08:00\",\"value\":\"CLEAR_DAY\"},{\"date\":\"2021-03-17T00:00+08:00\",\"value\":\"PARTLY_CLOUDY_DAY\"}],\"skycon_20h_32h\":[{\"date\":\"2021-03-13T00:00+08:00\",\"value\":\"MODERATE_HAZE\"},{\"date\":\"2021-03-14T00:00+08:00\",\"value\":\"LIGHT_HAZE\"},{\"date\":\"2021-03-15T00:00+08:00\",\"value\":\"CLEAR_NIGHT\"},{\"date\":\"2021-03-16T00:00+08:00\",\"value\":\"PARTLY_CLOUDY_NIGHT\"},{\"date\":\"2021-03-17T00:00+08:00\",\"value\":\"CLOUDY\"}],\"status\":\"ok\",\"temperature\":[{\"avg\":8.0,\"date\":\"2021-03-13T00:00+08:00\",\"max\":14.0,\"min\":8.0},{\"avg\":10.54,\"date\":\"2021-03-14T00:00+08:00\",\"max\":13.0,\"min\":8.0},{\"avg\":10.22,\"date\":\"2021-03-15T00:00+08:00\",\"max\":15.0,\"min\":2.0},{\"avg\":7.91,\"date\":\"2021-03-16T00:00+08:00\",\"max\":13.0,\"min\":2.0},{\"avg\":9.22,\"date\":\"2021-03-17T00:00+08:00\",\"max\":15.0,\"min\":3.0}],\"visibility\":[{\"avg\":2.3,\"date\":\"2021-03-13T00:00+08:00\",\"max\":24.13,\"min\":2.3},{\"avg\":2.8,\"date\":\"2021-03-14T00:00+08:00\",\"max\":3.44,\"min\":2.42},{\"avg\":15.99,\"date\":\"2021-03-15T00:00+08:00\",\"max\":23.0,\"min\":1.69},{\"avg\":20.65,\"date\":\"2021-03-16T00:00+08:00\",\"max\":23.0,\"min\":10.0},{\"avg\":8.07,\"date\":\"2021-03-17T00:00+08:00\",\"max\":11.5,\"min\":5.61}],\"wind\":[{\"avg\":{\"direction\":164.32,\"speed\":4.52},\"date\":\"2021-03-13T00:00+08:00\",\"max\":{\"direction\":199.06,\"speed\":8.28},\"min\":{\"direction\":55.41,\"speed\":0.57}},{\"avg\":{\"direction\":183.85,\"speed\":7.16},\"date\":\"2021-03-14T00:00+08:00\",\"max\":{\"direction\":204.17,\"speed\":14.88},\"min\":{\"direction\":159.15,\"speed\":3.34}},{\"avg\":{\"direction\":348.35,\"speed\":18.42},\"date\":\"2021-03-15T00:00+08:00\",\"max\":{\"direction\":346.58,\"speed\":32.29},\"min\":{\"direction\":224.21,\"speed\":1.79}},{\"avg\":{\"direction\":151.85,\"speed\":12.14},\"date\":\"2021-03-16T00:00+08:00\",\"max\":{\"direction\":186.26,\"speed\":21.09},\"min\":{\"direction\":140.55,\"speed\":4.74}},{\"avg\":{\"direction\":57.09,\"speed\":5.7},\"date\":\"2021-03-17T00:00+08:00\",\"max\":{\"direction\":130.61,\"speed\":10.61},\"min\":{\"direction\":14.05,\"speed\":2.39}}]}}\n"

            if (realTimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realTimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                val fromJsonWeather = Gson().fromJson(resultJson, Weather::class.java)
                Result.success(fromJsonWeather)

                /*  Result.failure(
                      RuntimeException(
                          "realtime response status is ${realTimeResponse.status}" +
                                  "daily response status is ${dailyResponse.status}"
                      )
                  )*/
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