package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.kochbuchappmagnus.domainlayer.domainentities.PrivateRecipe


@Dao
interface PrivateRecipeDAO {

    @Insert
    fun insert(privateRecipe:PrivateRecipe)

    @Delete
    fun delte(privateRecipe: PrivateRecipe)

    @Update
    fun update(privateRecipe: PrivateRecipe)
}