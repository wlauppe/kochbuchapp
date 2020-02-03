package de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*

@Dao
interface PublicRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(publicRecipe:PublicRecipeDB):Long

    @Query("SELECT * from publicRecipe")
    fun getAll():List<PublicRecipeDB>

    @Query("SELECT * from publicRecipe where id = :id")
    fun getRecipe(id:Long):PublicRecipeDB
}