package com.qtgm.hello_coroutine

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvHello.text = "hello coroutine ${System.currentTimeMillis()}"

//        setHelloTv()
        startActivity(Intent(this, Day2Activity::class.java))

        Log.e(TAG, "hello coroutine , thread = ${Thread.currentThread().name}")

        Thread {
            Log.e(TAG, "i am Thread , thread = ${Thread.currentThread().name}")
        }.start()

        thread {
            Log.e(TAG, "i am kotlin thread , thread = ${Thread.currentThread().name}")
        }

        GlobalScope.launch {
            Log.e(TAG, "i am GlobalScope launch , thread = ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Default) {
            Log.e(TAG, "dispatchers is Default , thread = ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.IO) {
            Log.e(TAG, "dispatchers is IO , thread = ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Unconfined) {
            Log.e(TAG, "dispatchers is Unconfined , thread = ${Thread.currentThread().name}")
        }

        GlobalScope.launch(Dispatchers.Main) {
            codeIO1()
            codeUI1()
        }

        doHomeWork()

    }

    private fun setHelloTv() {
        val format = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
        var time = format.format(System.currentTimeMillis())

        GlobalScope.launch(Dispatchers.Main) {
            updateTime(time)
            withContext(Dispatchers.IO) {
                delay(3000)
                time = format.format(System.currentTimeMillis())
            }
            updateTime(time)
        }

    }

    fun updateTime(time: String) {
        tvHello.text = "hello coroutine, time is $time"
    }

    suspend fun codeIO1() {
        withContext(Dispatchers.IO) {
            Log.e(TAG, "fun io() , thread = ${Thread.currentThread().name}")
        }
    }

    fun codeUI1() {
        Log.e(TAG, "fun ui() , thread = ${Thread.currentThread().name}")
    }


    fun doHomeWork() {
        GlobalScope.launch(Dispatchers.Main) {
            val data = getData()
            val processedData = processData(data)
            tvHello.text = processedData
        }
    }

    //io
    suspend fun getData(): String {
        return withContext(Dispatchers.IO) {
            "hen_coder"
        }
    }

    //io
    suspend fun processData(data: String): String {
        return withContext(Dispatchers.IO) {
            data.split("_").map {
                it.capitalize()
            }.reduce { acc, s -> acc + s }
        }
    }


}
