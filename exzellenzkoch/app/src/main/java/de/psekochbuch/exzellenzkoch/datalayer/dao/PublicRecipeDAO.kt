package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.*
import de.psekochbuch.exzellenzkoch.domainlayer.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.Recipe

@Dao
interface PublicRecipeDAO{

    @Insert
    fun insert(publicRecipe: PublicRecipe)

    @Delete
    fun delte(publicRecipe: PublicRecipe)

    @Update
    fun update(publicRecipe: PublicRecipe)

    @Query("SELECT * FROM publicrecipe ORDER BY id DESC")
    fun getAllRecipes():MutableList<Recipe>


}