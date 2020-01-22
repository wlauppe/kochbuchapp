package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.*
import de.psekochbuch.exzellenzkoch.domainlayer.PrivateRecipe


@Dao
interface PrivateRecipeDAO {

    @Insert
    fun insert(privateRecipe: PrivateRecipe)

    @Delete
    fun delte(privateRecipe: PrivateRecipe)

    @Update
    fun update(privateRecipe: PrivateRecipe)

    @Query("SELECT * FROM privaterecipe ORDER BY id DESC")
    fun getAllRecipes():List<PrivateRecipe>
}