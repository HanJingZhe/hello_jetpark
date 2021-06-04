package com.qtgm.base.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * @author peng_wang
 * @date 2021/6/4
 */
@SuppressLint("StaticFieldLeak")
object SPUtils {
    private lateinit var ctx: Context

    private const val spName = "shared_data"

    @JvmStatic
    fun initSP(ctx: Context) {
        SPUtils.ctx = ctx.applicationContext
    }

    private fun getSp(): SharedPreferences {
        if (!SPUtils::ctx.isInitialized) {
            throw RuntimeException("ms sdk not init sp!")
        }
        return ctx.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    @JvmStatic
    fun putString(key: String, value: String) = getSp().edit().putString(key, value).apply()

    @JvmStatic
    fun getString(key: String, def: String = ""): String? = getSp().getString(key, def)

    @JvmStatic
    fun has(key: String) = getSp().contains(key)

    @JvmStatic
    fun remove(key: String) = getSp().edit().remove(key).apply()

}
