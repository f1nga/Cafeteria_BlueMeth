package com.finga.cafeteria_bluemeth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.models.Dish

class BillViewModel : ViewModel() {

    private var _price : Int = 0

    fun sumPrice(suma: Int) {
        _price += suma
    }

    fun getPrice(): Int {
        return _price
    }
}