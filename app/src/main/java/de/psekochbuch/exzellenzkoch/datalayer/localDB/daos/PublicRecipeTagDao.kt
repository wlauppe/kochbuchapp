package de.psekochbuch.exzellenzkoch.datalayer.localDB.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PublicRecipeTagDB

@Dao
interface PublicRecipeTagDao {
    @Insert(onConflict  = OnConflictStrategy.REPLACE)
    fun insert(publicRecipeTag: PublicRecipeTagDB);

    @Query("SELECT * from publicRecipeTag where recipeId = :recipeId")
    fun getTagsFromRecipe(recipeId:Long): List<PublicRecipeTagDB>;

    @Query("DELETE FROM publicRecipeTag where recipeId = :recipeId")
    fun deleteTagsFromRecipe(recipeId:Long)

    @Query("DELETE FROM publicRecipeTag")
    fun deleteAll()

    //THis one is just for testing
    @Query("SELECT * from publicRecipeTag")
    fun getAll():List<PublicRecipeTagDB>
}