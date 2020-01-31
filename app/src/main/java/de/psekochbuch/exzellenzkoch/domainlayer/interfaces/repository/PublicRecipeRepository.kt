package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository
import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.datalayer.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import retrofit2.Call
import retrofit2.http.GET

interface PublicRecipeRepository {

    /**
     * Deletes the given recipe from the server.
     * Only authors or admins can delete recipes.
     */
    suspend fun removePublicRecipe(recipe: PublicRecipe)

    /**
     * Get a number of recipes from the server.
     * This method may use paging to load the requested recipes.
     *
     * TODO Suchparameter und Pagingparameter in Methodenkopf (nullables)
     */
    @Throws
    fun getPublicRecipes(): LiveData<List<PublicRecipe>>

    /**
     * Methods we still need:
     *
     * reportRecipe()
     * deleteRecipe(id:String)
     *
     */

}