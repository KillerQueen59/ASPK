package com.aspk.aspk.ui.home.menu.food

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aspk.aspk.data.local.model.FoodEntity
import com.aspk.aspk.data.local.room.FoodDatabase
import kotlinx.coroutines.launch

class FoodViewModel(private val database: FoodDatabase): ViewModel() {

    private val _food = MutableLiveData<List<FoodEntity>>()
    val food  = _food

    fun getFood() {
        viewModelScope.launch {
            try {
                _food.value = database.foodDao().getAllFood()


            } catch (e: Exception) {
                // handler error
            }
        }
    }

}