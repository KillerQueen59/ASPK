package com.aspk.aspk.data.local.room

import androidx.room.*
import com.aspk.aspk.data.local.model.FoodEntity

@Dao
interface FoodDao {

    @Query("SELECT * from food")
    fun getAllFood(): List<FoodEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun addCart(foodEntity: FoodEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(foodEntity: List<FoodEntity>)
}