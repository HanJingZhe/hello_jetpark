package com.example.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(var name: String, var page: Int, var author: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

