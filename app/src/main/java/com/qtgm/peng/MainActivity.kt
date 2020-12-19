package com.qtgm.peng

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.qtgm.peng.observer.MyLocationObserver
import com.qtgm.peng.service.MyLocationService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name

    var myLocationObserver: MyLocationObserver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e(TAG, "onCreate()")

        myLocationObserver =
            MyLocationObserver(this@MainActivity, object :
                MyLocationObserver.OnLocationChangeListener {
                override fun onLocationChange(a: Double, b: Double) {
                    Log.e(TAG, "onLoactionChange:a=$a,b=$b")
                }
            })
        lifecycle.addObserver(myLocationObserver!!)


        val locationIntent = Intent(this@MainActivity, MyLocationService::class.java)
        btnStartLocationService.setOnClickListener {
            startService(locationIntent)
        }

        btnStopLocationService.setOnClickListener {
            stopService(locationIntent)
        }
    }

}