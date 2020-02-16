package de.psekochbuch.exzellenzkoch.datalayer.localDB.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeDB

@Dao
interface PrivateRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(privateRecipe:PrivateRecipeDB):Long

    @Query("SELECT * from privateRecipe")
    fun getAll(): List<PrivateRecipeDB>

    @Query("SELECT * from privateRecipe where id = :id")
    fun getRecipe(id:Long):PrivateRecipeDB

    @Query("DELETE FROM privateRecipe where id = :id")
    fun deleteRecipe(id:Long)
}