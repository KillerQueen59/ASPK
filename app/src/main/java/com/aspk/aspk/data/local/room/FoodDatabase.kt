package com.aspk.aspk.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aspk.aspk.data.local.model.FoodEntity
import com.aspk.aspk.data.local.model.UserEntity

@Database(entities = [FoodEntity::class],version = 1)
abstract class FoodDatabase: RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {

        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "food.db"
                ).fallbackToDestructiveMigration()
                    .build().apply {
                        INSTANCE = this
                    }
            }
    }
}