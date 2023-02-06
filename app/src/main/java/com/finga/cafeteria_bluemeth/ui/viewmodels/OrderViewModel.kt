package com.finga.cafeteria_bluemeth.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.finga.cafeteria_bluemeth.data.models.Order
import com.finga.cafeteria_bluemeth.data.repositories.OrderRepository
import com.finga.cafeteria_bluemeth.utils.Methods

class OrderViewModel: ViewModel() {
    fun addOrder(context: Context, order: Order) {
        OrderRepository.addOrder(context, order)
    }

    fun getMaxId(context: Context): Int {
        return OrderRepository.getMaxId(context)
    }

    fun getOrdersByUser(context: Context, userEmail: String): List<Order> {
        return OrderRepository.getOrdersByUser(context, userEmail)
    }

    fun calcTotalPrice(orderList: List<Order>): Int {
        var totalPrice = 0

        for (order in orderList) {
            totalPrice += Methods.searchDishPrice(order.firstDish)
            totalPrice += Methods.searchDishPrice(order.secondDish)
            totalPrice += Methods.searchDishPrice(order.thirdDish)
        }

        return totalPrice
    }
}