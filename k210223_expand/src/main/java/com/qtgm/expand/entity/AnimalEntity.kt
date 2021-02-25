package com.qtgm.expand.entity

import com.qtgm.expand.R

data class AnimalEntity(var icon: Int, var name: String)

fun getAnimals(): List<AnimalEntity> {
    var list = mutableListOf<AnimalEntity>()
    repeat(3) {
        list.addAll(
            mutableListOf(
                AnimalEntity(R.mipmap.img_constellation_1, "星座1"),
                AnimalEntity(R.mipmap.img_constellation_2, "星座2"),
                AnimalEntity(R.mipmap.img_constellation_3, "星座3"),
                AnimalEntity(R.mipmap.img_constellation_4, "星座4"),
                AnimalEntity(R.mipmap.img_constellation_5, "星座5"),
                AnimalEntity(R.mipmap.img_constellation_6, "星座6"),
                AnimalEntity(R.mipmap.img_constellation_7, "星座7"),
                AnimalEntity(R.mipmap.img_constellation_8, "星座8"),
                AnimalEntity(R.mipmap.img_constellation_9, "星座9"),
                AnimalEntity(R.mipmap.img_constellation_10, "星座10"),
                AnimalEntity(R.mipmap.img_constellation_11, "星座11"),
                AnimalEntity(R.mipmap.img_constellation_12, "星座12"),
                AnimalEntity(R.mipmap.img_constellation_13, "星座13"),
                AnimalEntity(R.mipmap.img_constellation_14, "星座14"),
                AnimalEntity(R.mipmap.img_constellation_15, "星座15"),
                AnimalEntity(R.mipmap.img_constellation_16, "星座16"),
                AnimalEntity(R.mipmap.img_constellation_17, "星座17"),
                AnimalEntity(R.mipmap.img_constellation_18, "星座18"),
                AnimalEntity(R.mipmap.img_constellation_19, "星座19"),
                AnimalEntity(R.mipmap.img_constellation_20, "星座20"),
                AnimalEntity(R.mipmap.img_constellation_21, "星座21"),
                AnimalEntity(R.mipmap.img_constellation_22, "星座22"),
                AnimalEntity(R.mipmap.img_constellation_23, "星座23"),
                AnimalEntity(R.mipmap.img_constellation_24, "星座24"),
            )
        )
    }
    return list
}