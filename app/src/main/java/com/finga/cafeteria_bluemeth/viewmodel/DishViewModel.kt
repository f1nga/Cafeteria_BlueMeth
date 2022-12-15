package com.finga.cafeteria_bluemeth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.data.DishProvider
import com.finga.cafeteria_bluemeth.models.Dish

class DishViewModel : ViewModel() {

    fun firstDishes(): ArrayList<Dish> {
        return DishProvider.firstDishes()
    }

    fun secondDishes(): ArrayList<Dish> {
        return DishProvider.secondDishes()
    }

    fun thirdDishes(): ArrayList<Dish> {
        return DishProvider.thirdDishes()
    }


}