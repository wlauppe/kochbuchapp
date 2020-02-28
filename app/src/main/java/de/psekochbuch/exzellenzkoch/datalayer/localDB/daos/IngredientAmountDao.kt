package de.psekochbuch.exzellenzkoch.datalayer.localDB.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.IngredientAmountDB

@Dao
interface IngredientAmountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ingredientAmount: IngredientAmountDB)

    @Query("SELECT * from ingredientamount where chapterId = :ingredientChapterId")
    fun getIngredientAmountByIngredientChapterId(ingredientChapterId:Long):List<IngredientAmountDB>

    @Query("DELETE FROM ingredientamount")
    fun deleteAll()

    @Query("DELETE FROM ingredientamount where id in (SELECT iaid from (SELECT id as iaid, chapterId from ingredientamount) join ingredientChapter on (ingredientamount.chapterId = ingredientChapter.id) where recipeId = :recipeId)")
    fun deleteFromRecipe(recipeId: Long)

    //THis one is just for testing
    @Query("SELECT * from ingredientamount")
    fun getAll():List<IngredientAmountDB>
}