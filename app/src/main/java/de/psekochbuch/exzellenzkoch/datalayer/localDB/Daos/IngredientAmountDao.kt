package de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*

@Dao
interface IngredientAmountDao {
    @Insert(onConflict  = OnConflictStrategy.REPLACE)
    fun insert(ingredientAmount: IngredientAmount);

    @Query("SELECT * from ingredientAmount where id = :ingredientChapterId")
    fun getIngredientAmountFromIngredientChapter(ingredientChapterId:Int): IngredientAmount;
}