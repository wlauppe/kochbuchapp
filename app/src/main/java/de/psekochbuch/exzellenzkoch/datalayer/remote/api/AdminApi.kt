package de.psekochbuch.exzellenzkoch.datalayer.remote.api

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.UserDto
import retrofit2.http.*


interface AdminApi {

    @GET( "reported/recipes")
    suspend fun getReportedPublicRecipes(@Query("page") page:Int,
                                         @Query("readCount") readCount:Int): List<PublicRecipeDto>

    @GET("/reported/users")
    suspend fun getReportedUsers(@Query("page") page:Int,
                                 @Query("readCount") readCount:Int): List<UserDto>

    @DELETE("/reported/{recipeId}")
    suspend fun deReportPublicRecipe(@Path ("recipeId") recipeId:Int)

    @DELETE("/reported/{userId}")
    suspend fun deReportUser(@Path("userId") userId:String)





}