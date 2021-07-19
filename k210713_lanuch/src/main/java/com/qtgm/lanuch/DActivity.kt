package com.qtgm.lanuch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class DActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)
        Log.e(TAG, "onCreate() taskId=${taskId}")

        findViewById<View>(R.id.tvShow).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private val TAG = this.javaClass.simpleName

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart()")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e(TAG, "onNewIntent()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy()")
    }
}