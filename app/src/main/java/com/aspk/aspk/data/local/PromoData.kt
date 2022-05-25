package com.aspk.aspk.data.local

import com.aspk.aspk.R
import com.aspk.aspk.data.local.model.FoodEntity
import com.aspk.aspk.data.local.model.PromoModel

object PromoData {
    private val name = arrayOf(
        "Diskon 1",
        "Diskon 2")
    private val discount = arrayOf(
       0.1f,
        0.2f
    )

    private val image = arrayOf(
        R.drawable.disc,
        R.drawable.dics2
    )

    val listPromo: ArrayList<PromoModel>
        get() {
            val list =  arrayListOf<PromoModel>()
            for(position in name.indices){
                val food = PromoModel()
                food.name = name[position]
                food.discount = discount[position]
                food.image = image[position]
                list.add(food)
            }
            return list
        }
}