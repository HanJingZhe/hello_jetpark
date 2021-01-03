package com.qtgm.peng.observer

import android.app.Activity
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyLocationObserver(
    val act: Activity,
    val onLocationChangeListener: OnLocationChangeListener
) :
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
        onLocationChangeListener.onLocationChange(1_192.0, 168.0)
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