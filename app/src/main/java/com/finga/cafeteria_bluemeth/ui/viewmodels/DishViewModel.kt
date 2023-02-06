package com.finga.cafeteria_bluemeth.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.data.repositories.DishesRepository
import com.finga.cafeteria_bluemeth.data.models.Dish

class DishViewModel : ViewModel() {
    fun getDishesByCategory(context: Context, category: Int) : LiveData<List<Dish>> {
        return DishesRepository.getDishesByCategory(context, category)
    }
}