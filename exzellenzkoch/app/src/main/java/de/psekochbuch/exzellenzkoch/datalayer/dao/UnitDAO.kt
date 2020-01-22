package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface UnitDAO {

    @Insert
    fun insert(unit:Unit)

    @Delete
    fun delte(unit: Unit)

    @Update
    fun update(unit: Unit)
}