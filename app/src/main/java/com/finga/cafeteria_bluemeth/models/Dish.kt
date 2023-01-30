package com.finga.cafeteria_bluemeth.models

import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class Dish(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "quantity")
    var cantidad: Int = 1,
    @ColumnInfo(name = "category")
    var categoria: Int,
)