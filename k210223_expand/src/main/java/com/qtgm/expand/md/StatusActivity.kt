package com.qtgm.expand.md

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.qtgm.expand.R
import kotlinx.android.synthetic.main.activity_status.*

class StatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)


        /*getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        )*/

//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)


//        getWindow().setStatusBarColor(Color.RED)


//        StatusBarUtil.setColor(this,Color.TRANSPARENT,0)
//        StatusBarUtil.setTransparent(this)
//        StatusBarUtil.setTranslucentForImageView(this, 0,null )

        window.statusBarColor = Color.TRANSPARENT
        window
            .decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }


    private fun statusImg() {
        val option =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        getWindow().getDecorView().setSystemUiVisibility(option)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        getWindow().setStatusBarColor(Color.TRANSPARENT)
    }

}