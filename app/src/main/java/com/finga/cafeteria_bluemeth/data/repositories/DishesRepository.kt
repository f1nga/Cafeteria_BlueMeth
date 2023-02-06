package com.finga.cafeteria_bluemeth.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.finga.cafeteria_bluemeth.data.providers.room.RestaurantDatabase
import com.finga.cafeteria_bluemeth.data.models.Dish

class DishesRepository {
    companion object {
        var userDatabase: RestaurantDatabase? = null

        private fun initializeDB(context: Context): RestaurantDatabase {
            return RestaurantDatabase.getDatabase(context)
        }

        fun getDishesByCategory(context: Context, category: Int): LiveData<List<Dish>> {
            userDatabase = initializeDB(context)

            return userDatabase!!.restaurantDAO().getDishesByCategory(category)
        }
    }
}