package com.finga.cafeteria_bluemeth.ui.pages.home.tabs

import androidx.fragment.app.Fragment
import com.finga.cafeteria_bluemeth.models.Dish

interface SendDish {
    fun sendDataToBillFragment(plat: Dish?)
}