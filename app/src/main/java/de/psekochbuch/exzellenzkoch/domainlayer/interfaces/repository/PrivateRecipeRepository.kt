package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe

interface PrivateRecipeRepository {

    /**
     * Returns list of all of the user's private recipes from the local Room DB
     */
    fun getPrivateRecipes(): LiveData<List<PrivateRecipe>>

    /**
     * Deletes a private recipe from the local Room DB
     *
     * @param id is the recipe's ID
     */
    suspend fun deletePrivateRecipe(id:String)

    /**
     * Adds a new recipe to the local Room DB
     */
    suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe)

    /**
     * Returns only the recipe with the given ID
     */
    fun getRecipe(id:String):LiveData<PrivateRecipe>


}