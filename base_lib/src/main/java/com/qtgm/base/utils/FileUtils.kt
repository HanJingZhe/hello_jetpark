package com.qtgm.base.utils

import android.annotation.SuppressLint
import android.content.Context
import java.io.File

/**
 * @author peng_wang
 * @date 2021/6/4
 */
@SuppressLint("StaticFieldLeak")
object FileUtils {
    private val TAG = this.javaClass.simpleName

    private lateinit var mContext: Context

    private var PD = true
    private var ROOT = "base"

    private const val LOG = "log"
    private const val IMG = "img"

    fun init(ctx: Context, root: String = ROOT) {
        mContext = ctx.applicationContext
        ROOT = root
    }

    private fun getRootFolder(): File {
        val rootFolder = File(mContext.filesDir, ROOT)
        if (!rootFolder.exists()) {
            rootFolder.mkdirs()
        }
        return rootFolder
    }

    fun getLogFolder(): File {
        val logFolder = File(getRootFolder(), LOG)
        if (!logFolder.exists()) {
            logFolder.mkdirs()
        }
        return logFolder
    }

    fun getImgFolder(): File {
        val imgFolder = File(getRootFolder(), IMG)
        if (!imgFolder.exists()) {
            imgFolder.mkdirs()
        }
        return imgFolder
    }

}