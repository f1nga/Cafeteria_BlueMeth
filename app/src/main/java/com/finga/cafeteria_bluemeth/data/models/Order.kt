package com.finga.cafeteria_bluemeth.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @ColumnInfo(name = "primer_plato")
    val firstDish: String,
    @ColumnInfo(name = "segundo_plato")
    val secondDish: String,
    @ColumnInfo(name = "tercer_plato")
    val thirdDish: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "email_usuario")
    val userEmail: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
)
