package com.aspk.aspk.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "food")
@Parcelize
data class FoodEntity(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "price")
    var price: Int = 0,

    @ColumnInfo(name = "qty")
    var qty : Int = 0,

    @ColumnInfo(name = "desc")
    var desc: String = "",

    @ColumnInfo(name = "image")
    var image: Int = 0,

    @ColumnInfo(name = "cart")
    var cart: Boolean = false,

    @ColumnInfo(name = "drink")
    var drink: Boolean = false,

    @ColumnInfo(name = "avalableIn")
    var avalableIn: String = "",

    @ColumnInfo(name = "sweetness")
    var sweetness: String = "",

    @ColumnInfo(name = "note")
    var note: String = "",

):Parcelable