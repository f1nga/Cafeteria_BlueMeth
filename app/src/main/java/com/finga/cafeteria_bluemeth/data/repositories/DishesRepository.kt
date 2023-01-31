package com.finga.cafeteria_bluemeth.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.finga.cafeteria_bluemeth.data.providers.room.RestaurantDatabase
import com.finga.cafeteria_bluemeth.data.models.Dish
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishesRepository {
    companion object {
        var userDatabase: RestaurantDatabase? = null

        fun initializeDB(context: Context): RestaurantDatabase {
            return RestaurantDatabase.getDatabase(context)
        }

        fun insertDish(context: Context, dish: Dish) {
            userDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.restaurantDAO().addDish(dish)
            }
        }

        fun deleteAllDishes(context: Context) {
            userDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.restaurantDAO().deleteAllDishes()
            }
        }

        fun getDishes(context: Context): LiveData<List<Dish>> {
            userDatabase = initializeDB(context)

            return userDatabase!!.restaurantDAO().getDishes()
        }

        fun getDishesByCategory(context: Context, category: Int): LiveData<List<Dish>> {
            userDatabase = initializeDB(context)

            return userDatabase!!.restaurantDAO().getDishesByCategory(category)
        }

        fun deleteDish(context: Context, dish: Dish) {
            userDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.restaurantDAO().deleteDish(dish)
            }
        }
    }
}