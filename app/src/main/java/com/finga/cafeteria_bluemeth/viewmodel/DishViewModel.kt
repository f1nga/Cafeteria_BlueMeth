package com.finga.cafeteria_bluemeth.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.data.DishesRepository
import com.finga.cafeteria_bluemeth.models.Dish

class DishViewModel : ViewModel() {

    var platos: LiveData<List<Dish>>? = null

    fun addDish(context: Context, dish: Dish) {
        DishesRepository.insertDish(context, dish)
    }

    fun deleteAllDishes(context: Context){
        DishesRepository.deleteAllDishes(context)
    }

    fun getDishes(context: Context) : LiveData<List<Dish>> {
        return DishesRepository.getDishes(context)
    }

    fun deleteDish(context: Context, dish: Dish) {
        DishesRepository.deleteDish(context, dish)
    }

    fun getDishesByCategory(context: Context, category: Int) : LiveData<List<Dish>> {
        return DishesRepository.getDishesByCategory(context, category)
    }
}