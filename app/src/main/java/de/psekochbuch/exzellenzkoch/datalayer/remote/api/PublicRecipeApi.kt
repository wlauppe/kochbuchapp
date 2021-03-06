package de.psekochbuch.exzellenzkoch.datalayer.remote.api

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import retrofit2.http.*
import java.util.*

/**
 * Interface for the api from the public recipes
 */
interface PublicRecipeApi {

    /**
     * GET-Request to get a recipe with a specific id.
     * The URl ends with /api/recipes/{id}
     * @param id Id of the recipe
     * @return The recipe with the specific id
     */
    @GET("recipes/{id}")
    suspend fun getRecipe(@Path("id") id:Int) : PublicRecipeDto

    /**
     * POST-Request to add a new recipe.
     * The URL ends with /api/recipes
     * @param publicRecipe Recipe to add
     */
    @POST ("recipes")
    suspend fun addRecipe(@Body publicRecipe: PublicRecipeDto) : PublicRecipeDto

    /**
     * PUT-Request to update a recipe
     * The URL ends with /api/recipes/{id}
     * @param publicRecipe Recipe to update
     * @param id Id of the recipe to update
     */
    @PUT ("recipes/{id}")
    suspend fun updateRecipe(@Body publicRecipe: PublicRecipeDto, @Query(value = "id") id : Int)

    /**
     * DELETE-Request to delete a recipe
     * The URL ends with /api/recipes/{id}
     * @param id Id of the recipe
     */
    @DELETE ("recipes/{id}")
    suspend fun deleteRecipe(@Path(value = "id") id:Int)

    /**
     * GET-Request to search a recipe with criteria
     * The URL ends with /api/recipes
     * @param title Optional parameter title to search
     * @param tags Optional parameter tags to search
     * @param ingredients Optional parameter ingredients to search
     * @param creationDate Optional parameter creationDate to search
     * @param page The page of the already loaded recipes
     * @param readCount Count of the to loaded recipes
     * @return List of the recipes
     */
    @GET("recipes")
    suspend fun search(@Query("title") title:String?,
               @Query("tags") tags:List<String>?,
               @Query("ingredients") ingredients:List<String>?,
               @Query("creationDate") creationDate:Date?,
               @Query("page") page:Int,
               @Query("readCount") readCount:Int):List<PublicRecipeDto>

    /**
     * POST-Request to report a recipe
     * The URL ends with /api/recipes/report/{id}
     * @param id Id of the recipe
     */
    @POST("recipes/report/{id}")
    suspend fun reportRecipe(@Path(value = "id") id:Int)

    /**
     * Get recipes from user
     * @return list of recipedtos
     */
    @GET("recipes/user/{userId}")
    suspend fun getUserRecipes(@Path( "userId") userId : String) : List<PublicRecipeDto>

}