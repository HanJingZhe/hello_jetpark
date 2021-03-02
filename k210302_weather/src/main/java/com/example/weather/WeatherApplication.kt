package com.example.weather

import android.app.Application
import android.content.Context

class WeatherApplication : Application() {

    companion object {
        lateinit var mAppContext: Context

        const val TOKEN = "LKXyheFjA6QJWdMY"

    }

    override fun onCreate() {
        super.onCreate()
        mAppContext = applicationContext
    }

}