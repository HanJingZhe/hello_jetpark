package com.qtgm.base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.widget.Toast
import kotlin.concurrent.thread

/**
 * @author peng_wang
 * @date 2021/6/4
 */
@SuppressLint("StaticFieldLeak")
object ToastUtils {

    private lateinit var mContext: Context
    private const val ISTOAST = true

    @JvmStatic
    fun init(ctx: Context) {
        mContext = ctx
    }

    @JvmStatic
    fun toastS(msg: String) {
        t(msg, Toast.LENGTH_SHORT)
    }

    @JvmStatic
    fun toastL(msg: String) {
        t(msg, Toast.LENGTH_LONG)
    }

    private fun t(msg: String, length: Int) {
        thread {
            Looper.prepare()
            Toast.makeText(mContext, msg, length).show()
            Looper.loop()
            Looper.myLooper()?.quit()
        }
    }


}