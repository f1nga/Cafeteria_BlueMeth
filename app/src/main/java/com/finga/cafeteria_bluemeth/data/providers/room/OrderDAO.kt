package com.finga.cafeteria_bluemeth.data.providers.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finga.cafeteria_bluemeth.data.models.Dish
import com.finga.cafeteria_bluemeth.data.models.Order

@Dao
interface OrderDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addOrder(order: Order)

    @Query("DELETE FROM orders WHERE id = :id")
    fun deleteOrder(id: Int)

    @Query("SELECT * FROM orders WHERE email_usuario =:userEmail")
    fun getOrdersByUser(userEmail: String): List<Order>

    @Query("SELECT MAX(id) FROM orders")
    fun getMaxId(): Int

}