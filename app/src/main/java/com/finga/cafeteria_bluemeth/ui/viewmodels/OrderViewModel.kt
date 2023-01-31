package com.finga.cafeteria_bluemeth.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.data.repositories.DishesRepository
import com.finga.cafeteria_bluemeth.data.repositories.OrderRepository

class OrderViewModel: ViewModel() {
    fun addOrder(context: Context, order: Order) {
        OrderRepository.addOrder(context, order)
    }

    fun deleteOrder(context: Context, id: Int) {
        OrderRepository.deleteOrder(context, id)
    }

    fun getMaxId(context: Context): Int {
        return OrderRepository.getMaxId(context)
    }

    fun getOrdersByUser(context: Context, userEmail: String): LiveData<List<Order>> {
        return OrderRepository.getOrdersByUser(context, userEmail)
    }

    fun getOrdersByUser2(context: Context, userEmail: String): List<Order> {
        return OrderRepository.getOrdersByUser2(context, userEmail)
    }
}