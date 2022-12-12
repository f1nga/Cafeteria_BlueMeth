package com.finga.cafeteria_bluemeth.data

import com.finga.cafeteria_bluemeth.models.User

class DataUsers {
    var user: User = User("dsasda","sdasaddsa")

    fun login(user: User) {
        this.user = user
    }

    fun currentUser(): User {
        return user
    }
}