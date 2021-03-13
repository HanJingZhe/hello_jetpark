package com.example.weather.test

import com.example.weather.WeatherApplication
import com.example.weather.logic.Repository
import com.example.weather.logic.model.Weather
import com.example.weather.logic.network.WeatherNetwork
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val TOKEN = "LKXyheFjA6QJWdMY"
const val BASE_URL = "https://api.caiyunapp.com/"
const val lng = "116.4073963"
const val lat = "39.9041999"

fun main() {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(TestService::class.java)

    testRealtimeWeather(service)
//    testDailyWeather(service)

//    testAppFun(service)
}

fun testAppFun(service: TestService) {
    val result = Repository.refreshWeather(lng, lat)
    val weather = result.value?.getOrNull()
    if (weather == null) {
        print("-1")
    }
    print("1")
}

fun testRealtimeWeather(service: TestService) {
    service.getRealtimeWeather(lng, lat).enqueue(object : retrofit2.Callback<ResponseBody> {
        override fun onResponse(
            call: retrofit2.Call<ResponseBody>,
            response: retrofit2.Response<ResponseBody>
        ) {
            println("success Realtime=${response.body()?.string()}")
        }

        override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
            println("fail Realtime=${t.message}")
        }
    })

    service.getDailyWeather(lng, lat).enqueue(object : retrofit2.Callback<ResponseBody> {
        override fun onResponse(
            call: retrofit2.Call<ResponseBody>,
            response: retrofit2.Response<ResponseBody>
        ) {
            println("success Daily=${response.body()?.string()}")
        }

        override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
            println("fail Daily=${t.message}")
        }
    })

    GlobalScope.launch {
        val dailyWeather = WeatherNetwork.getDailyWeather(lng, lat)
        val realTime = WeatherNetwork.getRealtimeWeather(lng, lat)
        println("success Daily=${dailyWeather.status}")
        println("success realtime=${realTime.status}")

        val realtimeDeferred = async {
            WeatherNetwork.getRealtimeWeather(lng,lat)
        }

        val deferredDaily = async {
            WeatherNetwork.getDailyWeather(lng, lat)
        }

        val realtimeResult = realtimeDeferred.await()
        val dailyResult = deferredDaily.await()
        println("success realtime result=${realtimeResult.status}")
        println("success daily result=${dailyResult.status}")

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
                println("success weather=${weather}")
                println("success weather=${Gson().toJson(weather)}")
            }
        }

    }
}

fun testDailyWeather(service: TestService) {
    service.getDailyWeather(TOKEN).enqueue(object : retrofit2.Callback<ResponseBody> {
        override fun onResponse(
            call: retrofit2.Call<ResponseBody>,
            response: retrofit2.Response<ResponseBody>
        ) {
            println("success=${response.body()?.string()}")
        }

        override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
            println("fail=${t.message}")
        }
    })
}

interface TestService {
    @GET("v2.5/{token}/121.6544,25.1552/realtime.json")
    fun getRealTime(@Path("token") token: String): retrofit2.Call<ResponseBody>

    @GET("v2.5/{token}/121.6544,25.1552/daily.json")
    fun getDailyWeather(@Path("token") token: String): retrofit2.Call<ResponseBody>

    @GET("v2.5/${TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): retrofit2.Call<ResponseBody>

    @GET("v2.5/${WeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): retrofit2.Call<ResponseBody>


}