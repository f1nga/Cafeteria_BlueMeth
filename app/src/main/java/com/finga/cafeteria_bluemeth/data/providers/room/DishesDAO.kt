package com.finga.cafeteria_bluemeth.data.providers.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finga.cafeteria_bluemeth.data.models.Dish

@Dao
interface DishesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addDish(dish: Dish)

    @Query("SELECT * FROM dishes WHERE category = :category")
    fun getDishesByCategory(category: Int): LiveData<List<Dish>>

    @Update
    fun updateDish(dish: Dish)

    @Delete
    fun deleteDish(dish: Dish)
}