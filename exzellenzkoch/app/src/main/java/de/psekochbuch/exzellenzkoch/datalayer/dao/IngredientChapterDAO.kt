package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import de.psekochbuch.exzellenzkoch.domainlayer.IngredientChapter


@Dao
interface IngredientChapterDAO {

    @Insert
    fun insert(ingredientChapter: IngredientChapter)

    @Delete
    fun delete(ingredientChapter: IngredientChapter)

    @Update
    fun update(ingredientChapter: IngredientChapter)
}