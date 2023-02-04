package com.finga.cafeteria_bluemeth.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import com.finga.cafeteria_bluemeth.R
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.ui.pages.login.LoginActivity

class Methods {
    companion object {
        fun searchDishImage(platName: String): Int {
            when(platName) {
                "Macarrons" -> return R.drawable.macarrones
                "Lasagna" -> return R.drawable.lasagna
                "Canelons" -> return R.drawable.canelones
                "Menestre" -> return R.drawable.menestra
                "Ensalada" -> return R.drawable.ensalada
                "Entrecot" -> return R.drawable.entrecot
                "Chuletas" -> return R.drawable.chuletas
                "Pollo ast" -> return R.drawable.pollo
                "Pulpito" -> return R.drawable.pulpo
                "Solomillo" -> return R.drawable.solomillo
                "Natillas" -> return R.drawable.natillas
                "Sorbette" -> return R.drawable.sorbette
                "Calippo" -> return R.drawable.calippo
                "Limonada" -> return R.drawable.limonada
                "Coulant" -> return R.drawable.coulant
            }
            return R.drawable.ic_launcher_background
        }

        fun searchDishPrice(platName: String): Int {
            when(platName) {
                "Macarrons" -> return 7
                "Lasagna" -> return 12
                "Canelons" -> return 9
                "Menestre" -> return 6
                "Entrecot" -> return 17
                "Chuletas" -> return 17
                "Pollo ast" -> return 11
                "Pulpito" -> return 19
                "Natillas" -> return 6
                "Sorbette" -> return 3
                "Calippo" -> return 5
                "Limonada" -> return 4
            }
            return -1
        }
    }
}