package com.example.weather

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun main() {

//    okHttpGet()
//    okHttpPost()
//    httpURLConnection()
    retrofit()
}

fun httpURLConnection() {
    val TOKEN = "LKXyheFjA6QJWdMY"
    val response = StringBuilder()
    val url = URL("https://api.caiyunapp.com/v2.5/$TOKEN/121.6544,25.1552/realtime.json")
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.connectTimeout = 10 * 1000
    connection.readTimeout = 10 * 1000
    /*val output = DataOutputStream(connection.outputStream)
    output.writeBytes("$TOKEN")*/
    val input = connection.inputStream
    val reader = BufferedReader(InputStreamReader(input))
    reader.use {
        reader.forEachLine {
            response.append(it)
        }
    }
    println(response.toString())
    connection.disconnect()


}

fun okHttpGet() {
    val TOKEN = "LKXyheFjA6QJWdMY"

    val client = OkHttpClient.Builder()
        .build()

    val request = Request.Builder()
        .url("https://api.caiyunapp.com/v2.5/$TOKEN/121.6544,25.1552/realtime.json")
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("fail=${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            println("success=${response.body()?.string()}")
        }
    })
}

fun okHttpPost() {
    val TOKEN = "LKXyheFjA6QJWdMY"

    val client = OkHttpClient.Builder()
        .build()

    val formBody = FormBody.Builder()
        .add("TOKEN", TOKEN)
        .build()

    val request = Request.Builder()
        .url("https://api.caiyunapp.com/v2.5/121.6544,25.1552/realtime.json")
        .post(formBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("fail=${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            println("success=${response.body()?.string()}")
        }
    })
}


fun retrofit() {
    val TOKEN = "LKXyheFjA6QJWdMY"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.caiyunapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(TestService::class.java)
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

}