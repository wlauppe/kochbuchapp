package de.psekochbuch.exzellenzkoch.datalayer.localDB.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.*

@Dao
interface PublicRecipeTagDao {
    @Insert(onConflict  = OnConflictStrategy.REPLACE)
    fun insert(publicRecipeTag: PublicRecipeTagDB);

    @Query("SELECT * from publicRecipeTag where recipeId = :recipeId")
    fun getTagsFromRecipe(recipeId:Long): List<PublicRecipeTagDB>;
}