package com.qtgm.hello_coroutine

import android.util.Log

object MsLogUtils {
    private val TAG = MsLogUtils::class.java.name

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