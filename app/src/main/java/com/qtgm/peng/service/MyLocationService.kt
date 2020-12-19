package com.qtgm.peng.service

import androidx.lifecycle.LifecycleService
import com.qtgm.peng.observer.MyLocationServiceObserver

class MyLocationService : LifecycleService() {

    override fun onCreate() {
        super.onCreate()

        lifecycle.addObserver(MyLocationServiceObserver())
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}