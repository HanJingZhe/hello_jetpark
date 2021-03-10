package com.example.weather.test

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun main() {
    httpURLConnection()
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
