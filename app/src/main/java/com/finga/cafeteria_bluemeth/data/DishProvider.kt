package com.finga.cafeteria_bluemeth.data

import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.models.Dish

class DishProvider {
    companion object {
        private val firstDishes = arrayListOf<Dish>(
            Dish("Macarro", 50, R.drawable.macarrones),
            Dish("Macarro", 50, R.drawable.macarrones),
            Dish("Macarro", 50, R.drawable.macarrones),
            Dish("Macarro", 50, R.drawable.macarrones)
        )

        private val secondDishes = arrayListOf<Dish>(
            Dish("Entrecot", 500, R.drawable.entrecot),
            Dish("Entrecot", 500, R.drawable.entrecot),
            Dish("Entrecot", 500, R.drawable.entrecot),
            Dish("Entrecot", 500, R.drawable.entrecot)
        )

        private val thirdDishes = arrayListOf<Dish>(
            Dish("Crema catalana", 30, R.drawable.crema_catalana),
            Dish("Crema catalana", 30, R.drawable.crema_catalana),
            Dish("Crema catalana", 30, R.drawable.crema_catalana),
            Dish("Crema catalana", 30, R.drawable.crema_catalana),
        )

        fun firstDishes(): ArrayList<Dish> {
            return firstDishes
        }

        fun secondDishes(): ArrayList<Dish> {
            return secondDishes
        }

        fun thirdDishes(): ArrayList<Dish> {
            return thirdDishes
        }
    }
}