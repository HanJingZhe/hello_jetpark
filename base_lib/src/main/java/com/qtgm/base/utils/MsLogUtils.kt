package com.qtgm.base.utils

import android.util.Log
import com.qtgm.base.BuildConfig

object MsLogUtils {

    private val TAG = this.javaClass.name

    private const val MAXLENGTH = 1024 * 3
    private val ISDEBUG = BuildConfig.DEBUG

    fun e(msg: String) {
        e(TAG, msg)
    }

    fun e(tag: String = TAG, msg: String) {
        var str = msg
        while (str.length > MAXLENGTH) {
            Log.e("$tag=====================", str.substring(0, MAXLENGTH))
            str = str.substring(MAXLENGTH)
        }
        Log.e("$tag=====================", str)
    }

}