package com.finga.cafeteria_bluemeth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BillViewModel : ViewModel() {

    private val _numero = MutableLiveData<Int>()

    init {
        getNumero()
    }

    fun setNumero(num: Int) {
        _numero.value = num
    }

    fun getNumero() {
        setNumero(2)
    }

    fun getNumeroLiveData(): LiveData<Int> {
        return _numero
    }
}