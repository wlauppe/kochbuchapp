package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.kochbuchappmagnus.domainlayer.domainentities.User

@Dao
interface UserDAO {

    @Insert
    fun insert(user:User)

    @Delete
    fun delete(user: User)

    @Update
    fun update(user: User)
}