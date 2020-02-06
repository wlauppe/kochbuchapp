package de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*

@Dao
interface PrivateRecipeTagDao {
    @Insert(onConflict  = OnConflictStrategy.REPLACE)
    fun insert(privateRecipeTag: PrivateRecipeTagDB);

    @Query("SELECT * from privateRecipeTag where recipeId = :recipeId")
    fun getTagsFromRecipe(recipeId:Long): List<PrivateRecipeTagDB>;

    @Query("DELETE FROM privateRecipeTag where recipeId = :recipeId")
    fun deleteTagsFromRecipe(recipeId:Long)
}