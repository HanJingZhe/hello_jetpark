package com.example.weather.test

import com.example.weather.WeatherApplication
import com.example.weather.logic.Repository
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

//    testDailyWeather(service)
//    testRealtimeWeather(service)

    testAppFun(service)
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