package com.aspk.aspk.ui.home.analysis

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class FoodData(
    val date: String,
    val itemDesc: String,
    val quantity: Long
)

@Parcelize
data class FoodDataLeast(
    val itemDesc: String,
    val quantity: Long
):Parcelable

@Parcelize
data class MainData(
    val data: ArrayList<List<FoodDataLeast>>?
): Parcelable
