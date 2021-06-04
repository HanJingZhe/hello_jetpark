package com.qtgm.k210103_hello_navigation

import android.app.Application
import android.content.Context
import com.qtgm.base.utils.ToastUtils

class MyApplication : Application() {

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext

        ToastUtils.initToast(mContext)
    }


}