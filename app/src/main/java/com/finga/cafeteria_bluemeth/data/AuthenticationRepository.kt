package com.finga.cafeteria_bluemeth.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.finga.cafeteria_bluemeth.database.RestaurantDatabase
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthenticationRepository {
    companion object {
        var userDatabase: RestaurantDatabase? = null

        fun initializeDB(context: Context): RestaurantDatabase {
            return RestaurantDatabase.getDatabase(context)
        }

        fun insertUser(context: Context, user: User) {
            userDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                userDatabase!!.usersDAO().addUser(user)
            }
        }

        fun login(context: Context, email: String, password: String): LiveData<User> {
            userDatabase = initializeDB(context)

            return userDatabase!!.usersDAO().getUser(email, password)
        }
    }

}