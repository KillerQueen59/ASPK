package com.aspk.aspk.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aspk.aspk.data.local.model.UserEntity

@Dao
interface DaoUser {

    @Query("SELECT * from user where username =:username")
    fun getUserByUsername(username: String): UserEntity

    @Query("SELECT * from user where email =:email")
    fun getUserByEmail(email: String): UserEntity

    @Query("SELECT EXISTS(SELECT * FROM user where username =:username)")
    fun checkUserExistByUsername(username: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM user where email =:email)")
    fun checkUserExistByEmail(email: String): Boolean

    @Insert
    fun addUser(userEntity: UserEntity)


}