package com.qtgm.peng.observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationServiceObserver : LifecycleObserver {

    val TAG = MyLocationServiceObserver::class.java.name


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startGetLocation() {
        Log.e(TAG, "startGetLocation()")
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopGetLocation() {
        Log.e(TAG, "stopGetLocation()")
    }


}