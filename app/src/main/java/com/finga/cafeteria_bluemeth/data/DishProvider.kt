package com.finga.cafeteria_bluemeth.data

import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.models.Dish

class DishProvider {
    companion object {
        private val firstDishes = arrayListOf<Dish>(
            Dish("Mocorro", 7, R.drawable.macarrones,),
            Dish("Conolo", 9, R.drawable.canelones),
            Dish("Losogno", 12, R.drawable.lasagna),
            Dish("Monostro", 6, R.drawable.menestra)
        )

        private val secondDishes = arrayListOf<Dish>(
            Dish("Entrecot", 17, R.drawable.entrecot),
            Dish("Chuletas", 13, R.drawable.chuletas),
            Dish("Pollo ast", 11, R.drawable.pollo),
            Dish("Pulpito", 19, R.drawable.pulpo)
        )

        private val thirdDishes = arrayListOf<Dish>(
            Dish("Sorbette", 6, R.drawable.sorbette),
            Dish("Calippo", 3, R.drawable.calippo),
            Dish("Natillas", 5, R.drawable.natillas),
            Dish("Limonada", 4, R.drawable.limonada),
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