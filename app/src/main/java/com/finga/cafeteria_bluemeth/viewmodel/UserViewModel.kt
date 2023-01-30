package com.finga.cafeteria_bluemeth.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.data.AuthenticationRepository
import com.finga.cafeteria_bluemeth.data.DishesRepository
import com.finga.cafeteria_bluemeth.models.Dish
import com.finga.cafeteria_bluemeth.models.User

class UserViewModel: ViewModel() {

    var user: User? = null

    fun addUser(context: Context, user: User) {
        AuthenticationRepository.insertUser(context, user)
    }

    fun login(context: Context, email: String, password: String) :LiveData<User> {
        return AuthenticationRepository.login(context, email, password)
    }

    fun setCurrentUser(currentUser: User) {
        user = currentUser
    }

    fun getCurrentUser() : User? {
        return user
    }
}