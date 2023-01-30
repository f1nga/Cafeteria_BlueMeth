package com.finga.cafeteria_bluemeth.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finga.cafeteria_bluemeth.models.Dish
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDish(dish: Dish)

    @Query("SELECT * FROM dishes")
    fun getDishes(): LiveData<List<Dish>>

    @Query("SELECT * FROM dishes WHERE category = :category")
    fun getDishesByCategory(category: Int): LiveData<List<Dish>>

    @Update
    fun updateDish(dish: Dish)

    @Delete
    fun deleteDish(dish: Dish)

    @Query("DELETE FROM dishes")
    fun deleteAllDishes()

}