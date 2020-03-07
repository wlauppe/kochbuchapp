package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe

interface PrivateRecipeRepository {

    /**
     * Returns list of all of the user's private recipes from the local Room DB
     */
    fun getPrivateRecipes(): LiveData<List<PrivateRecipe>>

    /**
     * Returns only the recipe with the given ID
     */
    fun getPrivateRecipe(id : Int): LiveData<PrivateRecipe>

    /**
     * Deletes a private recipe from the local Room DB
     *
     * @param id is the recipe's ID
     */
    suspend fun deletePrivateRecipe(id: Int)

    /**
     * Updates a recipe in the local Room DB
     */
       /**
     * Adds a new recipe to the local Room D
        * if
     */
       //Achtung, das ist gleichzeitig insert und update.
       //Wenn das Private Rezept die ID null hat, wirds neu hinzugefügt.
       //Ansonsten das bestehende geupdatet.
    suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe)
    suspend fun insertPrivateRecipeAndReturnId(privateRecipe: PrivateRecipe) : Int

    /**
     * deletes all private recipes
     */
    fun deleteAll()

    /**
     * returns all published IDs from the recipes in the database
     */
    fun getAllPublishedIds():LiveData<List<Int>>
}