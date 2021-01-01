package com.qtgm.hello_coroutine

import android.widget.Toast

object MsToastUtils {

    val ISDEBUG = BuildConfig.DEBUG

    fun toastS(msg: String) {
        if (!ISDEBUG) return
        Toast.makeText(MsApplication.mContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun toastL(msg: String) {
        if (!ISDEBUG) return
        Toast.makeText(MsApplication.mContext, msg, Toast.LENGTH_LONG).show()
    }

}