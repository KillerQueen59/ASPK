package com.aspk.aspk.util

import android.content.Context
import android.content.SharedPreferences
import com.aspk.aspk.data.local.model.UserEntity


class SessionManagement(var context: Context) {
    companion object{
        private val PREF_NAME = "AndroidHivePref"
        private val IS_LOGIN = "isLoggedIn"
        private val IS_FIRST = "isFirst"

        val KEY_ID = "id"
        val KEY_EMAIL = "email"
        val KEY_NAME = "name"
        val KEY_PROMO = "promo"
        val KEY_PROMO_NAME = "promoName"

    }

    var pref: SharedPreferences

    var editor: SharedPreferences.Editor

    var PRIVATE_MODE = 0

    init {
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }

    val isLoggedIn: Boolean
        get() = pref.getBoolean(IS_LOGIN, false)

    val isFirst: Boolean
        get() = pref.getBoolean(IS_FIRST,true)

    val name: String
        get() = pref.getString(KEY_NAME,"")!!

    val promo: Float
        get() = pref.getFloat(KEY_PROMO,0f)

    val promoName: String
        get() = pref.getString(KEY_PROMO_NAME,"").toString()


    fun createAuth(email: String,name: String){
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.putBoolean(IS_LOGIN, true)
        editor.commit()
    }

    fun applyPromo(promo: Float,promoName: String){
        editor.putFloat(KEY_PROMO, promo)
        editor.putString(KEY_PROMO_NAME, promoName)
        editor.commit()
    }

    fun clearPromo(){
        editor.remove(KEY_PROMO)
        editor.remove(KEY_PROMO_NAME)
        editor.commit()
    }

    fun firstLogin(email: String,name: String){
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.putBoolean(IS_LOGIN, true)
        editor.putBoolean(IS_FIRST, false)
        editor.commit()
    }

    fun clearAuthClient(){
        editor.remove(KEY_EMAIL)
        editor.remove(KEY_NAME)
        editor.commit()
    }


}