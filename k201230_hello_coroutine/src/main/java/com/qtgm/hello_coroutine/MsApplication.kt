package com.qtgm.hello_coroutine

import android.app.Application
import android.content.Context

class MsApplication : Application() {

    companion object {
        var mContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

}