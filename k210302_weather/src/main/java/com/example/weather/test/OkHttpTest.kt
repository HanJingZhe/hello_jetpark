package com.example.weather.test

import okhttp3.*
import java.io.IOException

fun main() {
    okHttpGet()
    okHttpPost()
}

fun okHttpGet() {

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