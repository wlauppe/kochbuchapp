package de.psekochbuch.exzellenzkoch.datalayer.localDB.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.*

@Dao
interface IngredientChapterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ingredientChapter:IngredientChapterDB):Long

    @Query("SELECT * from ingredientChapter where recipeId = :recipeId")
    fun getIngredientChapterByRecipeId(recipeId:Int):List<IngredientChapterDB>
}