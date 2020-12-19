package com.qtgm.peng.observer

import android.app.Activity
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationObserver(act: Activity, onLocationChangeListener: OnLocationChangeListener) :
    LifecycleObserver {

    val TAG = MyLocationObserver::class.java.name

    companion object {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initLocation() {
        Log.e(TAG, "init location()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun getLocation() {
        Log.e(TAG, "get location()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun closeLocation() {
        Log.e(TAG, "close location()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun release() {
        Log.e(TAG, "release location")
    }

    interface OnLocationChangeListener {
        fun onLocationChange(a: Double, b: Double)
    }


}