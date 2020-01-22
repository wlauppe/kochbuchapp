package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import de.psekochbuch.exzellenzkoch.domainlayer.IngredientAmount

@Dao
interface IngrediendAmountDAO {

    @Insert
    fun insert(ingredientAmount: IngredientAmount)

    @Delete
    fun delete(ingredientAmount: IngredientAmount)

    @Update
    fun update(ingredientAmount: IngredientAmount)


}