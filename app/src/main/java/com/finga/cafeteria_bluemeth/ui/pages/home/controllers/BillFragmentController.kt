package com.finga.cafeteria_bluemeth.ui.pages.home.controllers

import android.widget.TextView
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.models.Dish

class BillFragmentController {

      fun updatePrice(listPlats: ArrayList<Dish>): Int {
        var preuFinal = 0

        for (items in listPlats) {
            preuFinal += items.price * items.cantidad
        }

        return preuFinal
    }

    fun dishExists(listPlats: ArrayList<Dish>, plat: Dish): Dish? {
        for (item in listPlats) {
            if(item.name == plat.name) {
                return item
            }
        }

        return null
    }

    fun add(txtNum: TextView): Int {
        return txtNum.text.toString().toInt() + 1
    }

    fun remove(txtNum: TextView): Int {
        return txtNum.text.toString().toInt() -1
    }

    fun searchImage(imgName: String): Int {
        when(imgName) {
            "Mocorro" -> return R.drawable.macarrones
            "Losogno" -> return R.drawable.lasagna
            "Conolo" -> return R.drawable.canelones
            "Monostro" -> return R.drawable.menestra
            "Entrecot" -> return R.drawable.entrecot
            "Chuletas" -> return R.drawable.chuletas
            "Pollo ast" -> return R.drawable.pollo
            "Pulpito" -> return R.drawable.pulpo
            "Natillas" -> return R.drawable.natillas
            "Sorbette" -> return R.drawable.sorbette
            "Calippo" -> return R.drawable.calippo
            "Limonada" -> return R.drawable.limonada
        }
        return 0
    }
}