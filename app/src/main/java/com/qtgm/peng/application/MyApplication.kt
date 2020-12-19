package com.qtgm.peng.application

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.qtgm.peng.observer.MyApplicationObserver

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(MyApplicationObserver())
    }


}