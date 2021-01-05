package com.qtgm.base.utils

import android.app.Activity
import android.widget.Toast
import com.qtgm.base.BuildConfig

object MsToastUtils {

    private val ISDEBUG = BuildConfig.DEBUG
    private var mContext = Activity()

    fun toastS(msg: String) {
        if (!ISDEBUG) return
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun toastL(msg: String) {
        if (!ISDEBUG) return
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show()
    }

}