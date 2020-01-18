package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.kochbuchappmagnus.domainlayer.domainentities.PublicRecipe

@Dao
interface PublicRecipeDAO{

    @Insert
    fun insert(publicRecipe: PublicRecipe)

    @Delete
    fun delte(publicRecipe: PublicRecipe)

    @Update
    fun update(publicRecipe: PublicRecipe)

}