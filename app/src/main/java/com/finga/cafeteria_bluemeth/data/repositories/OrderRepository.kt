package com.finga.cafeteria_bluemeth.data.repositories

import android.content.Context
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.data.providers.room.RestaurantDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderRepository {
    companion object {
        var orderDatabase: RestaurantDatabase? = null

        private fun initializeDB(context: Context): RestaurantDatabase {
            return RestaurantDatabase.getDatabase(context)
        }

        fun addOrder(context: Context, order: Order) {
            orderDatabase = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                orderDatabase!!.orderDAO().addOrder(order)
            }
        }

        fun getMaxId(context: Context) : Int {
            orderDatabase = initializeDB(context)

            return orderDatabase!!.orderDAO().getMaxId()
        }

        fun getOrdersByUser(context: Context, userEmail: String) : List<Order> {
            orderDatabase = initializeDB(context)

            return orderDatabase!!.orderDAO().getOrdersByUser(userEmail)
        }
    }
}