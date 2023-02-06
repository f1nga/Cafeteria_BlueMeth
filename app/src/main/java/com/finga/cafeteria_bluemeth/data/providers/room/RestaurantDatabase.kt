package com.finga.cafeteria_bluemeth.data.providers.room

import android.content.Context
import androidx.room.*
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.data.models.User


@Database(
    entities = [Dish::class, User::class, Order:: class],
    version = 1,
    exportSchema = true
)

abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun restaurantDAO(): DishesDAO
    abstract fun usersDAO(): UsersDAO
    abstract fun orderDAO(): OrderDAO

    companion object {

        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        fun getDatabase(context: Context): RestaurantDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): RestaurantDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                RestaurantDatabase::class.java,
                "bluemeth_database"
            ).allowMainThreadQueries()
                .createFromAsset("database/restaurant.db")
                .build()
        }
    }
}