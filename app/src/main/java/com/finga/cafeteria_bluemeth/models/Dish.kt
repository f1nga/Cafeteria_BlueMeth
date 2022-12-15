package com.finga.cafeteria_bluemeth.models

import android.widget.ImageView

data class Dish(val name: String, val price: Int, val image: Int, var cantidad: Int = 0) {
}