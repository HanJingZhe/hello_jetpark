package com.qtgm.expand.md

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.qtgm.base.base.BaseActivity
import com.qtgm.expand.R
import kotlinx.android.synthetic.main.activity_animal.*

class AnimalActivity : BaseActivity() {

    override fun setLayoutId(): Int = R.layout.activity_animal

    override fun initView() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = "星座1"
        ivAnimal.setImageResource(R.mipmap.img_constellation_1)
        tvShowAnimal.text = getAnimalDetail()
    }

    override fun initData() {
    }

    private fun setStatusBar() {
        //<5.0
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //>5.0
        val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(option);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);


        /*val rootView: ViewGroup =
            window.decorView.findViewById(android.R.id.content) as ViewGroup
        rootView.setPadding(0, getStatusBarHeight(), 0, 0)*/
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//        window.statusBarColor = Color.TRANSPARENT

//        val decorView = window.decorView
//        val systemUiVisibility = decorView.systemUiVisibility
//        decorView.systemUiVisibility = systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getAnimalDetail(): String = "animalDetail".repeat(1000)

    /**
     * 利用反射获取状态栏高度
     */
    private fun getStatusBarHeight(): Int {
        var result = 0
        //获取状态栏高度的资源id
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}