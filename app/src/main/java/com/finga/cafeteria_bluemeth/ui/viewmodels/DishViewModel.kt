package com.finga.cafeteria_bluemeth.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.data.repositories.DishesRepository
import com.finga.cafeteria_bluemeth.data.models.Dish

class DishViewModel : ViewModel() {

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