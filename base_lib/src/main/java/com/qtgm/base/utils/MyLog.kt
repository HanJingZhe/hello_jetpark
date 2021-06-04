package com.qtgm.base.utils

import android.text.format.DateFormat
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

/**
 * @author peng_wang
 * @date 2021/6/4
 */
object MyLog {

    private val TAG = this.javaClass.simpleName

    private const val MAXLENGTH = 1024 * 3

    private val LEVEL = Level.D
    private const val ISWRITE = false
    private const val DATE_FORMAT = "yy-MM-dd HH:mm:ss"

    private val logFileName: String by lazy {
        "ms_log_${DateFormat.format("yyMMddHHmmss", System.currentTimeMillis())}.log"
    }
    private val writer: BufferedWriter by lazy {
        BufferedWriter(FileWriter(File(FileUtils.getLogFolder(), logFileName), true))
    }

    @JvmStatic
    fun v(msg: String) {
        v(TAG, msg)
    }

    @JvmStatic
    fun v(tag: String, msg: String) {
        log(Level.V, tag, msg)
    }

    @JvmStatic
    fun d(msg: String) {
        d(TAG, msg)
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        log(Level.D, tag, msg)
    }

    @JvmStatic
    fun i(msg: String) {
        i(TAG, msg)
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        log(Level.I, tag, msg)
    }

    @JvmStatic
    fun e(msg: String) {
        e(TAG, msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        log(Level.E, tag, msg)
    }

    private fun log(level: Level, tag: String, msg: String) {
        if (level < LEVEL) return
        var str = msg
        val nTag = "${if (tag == TAG) TAG else "$TAG-$tag"}========"

        while (str.length > MAXLENGTH) {
            when (level) {
                Level.V -> Log.v(nTag, str.substring(0, MAXLENGTH))
                Level.D -> Log.d(nTag, str.substring(0, MAXLENGTH))
                Level.I -> Log.i(nTag, str.substring(0, MAXLENGTH))
                Level.E -> Log.e(nTag, str.substring(0, MAXLENGTH))
            }
            if (ISWRITE) write(level, nTag, str)
            str = str.substring(MAXLENGTH)
        }

        when (level) {
            Level.V -> Log.v(nTag, str)
            Level.D -> Log.d(nTag, str)
            Level.I -> Log.i(nTag, str)
            Level.E -> Log.e(nTag, str)
        }
        if (ISWRITE) write(level, nTag, str)
    }

    private fun write(level: Level, tag: String, msg: String) {
        val time = DateFormat.format(DATE_FORMAT, System.currentTimeMillis())
        val content = "$time level=$level $tag : $msg"
        writer.write(content)
        writer.newLine()
        writer.flush()
    }

    enum class Level {
        V, D, I, E
    }

}