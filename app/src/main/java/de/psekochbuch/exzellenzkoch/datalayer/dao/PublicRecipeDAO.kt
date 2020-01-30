package de.psekochbuch.exzellenzkoch.datalayer.dao
import androidx.room.*
import de.psekochbuch.exzellenzkoch.domainlayer.entity.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.entity.Recipe

@Dao
interface PublicRecipeDAO{

    @Insert
    fun insert(publicRecipe: PublicRecipe)

    @Delete
    fun delte(publicRecipe: PublicRecipe)

    @Update
    fun update(publicRecipe: PublicRecipe)

    @Query("SELECT * FROM publicrecipe ORDER BY id DESC")
    fun getAllRecipes():MutableList<Recipe>


}