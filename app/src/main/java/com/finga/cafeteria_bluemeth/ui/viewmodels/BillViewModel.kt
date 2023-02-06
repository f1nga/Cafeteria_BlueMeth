package com.finga.cafeteria_bluemeth.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.data.models.Dish

class BillViewModel : ViewModel() {

    var listPlats : ArrayList<Dish> = ArrayList()
    var preuTotal : Int = 0

    fun addDishToBill(dish: Dish) {
        listPlats.add(dish)

        updatePrice(dish.price)
    }

    fun removeDishToBill(dish: Dish) {
        listPlats.remove(dish)
    }

    fun getPlatsFromBill(): ArrayList<Dish> {
        return listPlats
    }

    private fun updatePrice(newPrice: Int) {
        preuTotal += newPrice
    }

    fun getPreu(): Int {
        return preuTotal
    }

    fun isFullOrder(): Boolean {
        return listPlats.size >= 3
    }
}