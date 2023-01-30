package com.finga.cafeteria_bluemeth.database

import android.content.Context
import androidx.room.*
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.models.User


@Database(
    entities = [Dish::class, User::class],
    version = 1,
    exportSchema = true
)

abstract class RestaurantDatabase : RoomDatabase() {

    abstract fun restaurantDAO(): RestaurantDAO
    abstract fun usersDAO(): UsersDAO

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
            ).createFromAsset("database/restaurant.db")
                .build()
        }
    }
}