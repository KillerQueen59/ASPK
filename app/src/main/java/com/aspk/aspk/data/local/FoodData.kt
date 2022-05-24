package com.aspk.aspk.data.local

import com.aspk.aspk.data.local.model.FoodEntity


object FoodData {
    private val name = arrayOf("Choco Chip Cookies","Strawberry Cup Cake","Roti isi Abon","Chocolate","Matcha Latte","Americano")
    private val detail = arrayOf(
       "Chocolate, brown sugar, choco chips",
       "Strawberry, vanilla cream",
        "Sepotong roti abon ",
        "Chocolate, brown sugar, choco chips",
        "Matcha, susu full cream, creamer bubuk dengan gula \n" +
                "cair",
        "Bubuk kopi, sugar"
        )
    private val price = arrayOf(
        15000,
        14000,
        10000,
        20000,
        23000,
        25000
    )
    private val drink = arrayOf(
        false,
        false,
        false,
        true,
        true,
        true
    )

    val listFood: ArrayList<FoodEntity>
        get() {
            val list =  arrayListOf<FoodEntity>()
            for(position in name.indices){
               val food = FoodEntity()
                food.name = name[position]
                food.desc = detail[position]
                food.price = price[position]
                food.drink = drink[position]
                list.add(food)
            }
            return list
        }
}