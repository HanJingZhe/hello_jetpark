package com.qtgm.expand.md

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.qtgm.expand.R
import kotlinx.android.synthetic.main.activity_animal.*

class AnimalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = "星座1"
        ivAnimal.setImageResource(R.mipmap.img_constellation_1)
        tvShowAnimal.text = getAnimalDetail()
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


}