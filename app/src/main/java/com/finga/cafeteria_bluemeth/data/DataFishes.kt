package com.finga.cafeteria_bluemeth.data

import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.models.Dish

class DataFishes {
    fun firstDishes(): List<Dish> {
        return listOf<Dish>(
            Dish("Macarro", 50.00, R.drawable.macarrones),
            Dish("Macarro", 50.00, R.drawable.macarrones),
            Dish("Macarro", 50.00, R.drawable.macarrones),
            Dish("Macarro", 50.00, R.drawable.macarrones)
        )
    }

    fun secondDishes(): List<Dish> {
        return listOf<Dish>(
            Dish("Entrecot", 500.00, R.drawable.entrecot),
            Dish("Entrecot", 500.00, R.drawable.entrecot),
            Dish("Entrecot", 500.00, R.drawable.entrecot),
            Dish("Entrecot", 500.00, R.drawable.entrecot)
        )
    }

    fun thirdDishes(): List<Dish> {
        return listOf<Dish>(
            Dish("Crema catalana", 30.00, R.drawable.crema_catalana),
            Dish("Crema catalana", 30.00, R.drawable.crema_catalana),
            Dish("Crema catalana", 30.00, R.drawable.crema_catalana),
            Dish("Crema catalana", 30.00, R.drawable.crema_catalana),
        )
    }
}