package com.finga.cafeteria_bluemeth.models

class User (email: String, password: String) {

    var plats: ArrayList<Dish> = ArrayList()

    fun addPlat(plat: Dish) {
        plats.add(plat)
    }

}