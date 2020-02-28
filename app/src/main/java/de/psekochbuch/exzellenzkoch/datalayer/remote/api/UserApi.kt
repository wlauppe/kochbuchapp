package de.psekochbuch.exzellenzkoch.datalayer.remote.api

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.CustomTokenDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.UserDto
import retrofit2.http.*


/**
 * Interface for the api from the users
 */

interface UserApi {

    /**
     * POST-Request to create a new user
     * The URL ends with /api/users
     * @param user The user to create
     */
    @POST("users")
    fun createUser(@Path("") @Body user: UserDto)

    @POST("users/{userId}")
    suspend fun addUser(@Path ("userId") userId:String) :CustomTokenDto

    /**
     * PUT-Request to update an user
     * The URL ends with /api/users/{userId}
     * @param user The user to update
     * @param userId The id of the user
     */
    @PUT("users/{userId}")
    fun updateUser( userId:String, @Body user:UserDto)

    /**
     * DELETE-Request to delete an user
     * The URL ends with /api/users/{userId}
     * @param userId The id of the user who should delete
     */
    @DELETE("users/{userId}")
    fun deleteUser(@Path("userId") userId:String)

/**
     * GET-Request to get a user with specific id
     * The URL ends with /api/users/{userId}
     * @param userId The id of the user
     */
    @GET("users/check/{userId}")
    suspend fun checkUser(@Path ("userId") userId: String) : UserDto



    /**
     * GET-Request to get a user with specific id
     * The URL ends with /api/users/{userId}
     * @param userId The id of the user
     */
    @GET("users/{userId}")
    suspend fun getUser(@Path ("userId") userId: String) : UserDto

    @POST("users/report/{id}")
   suspend fun reportUser(@Path(value = "id") id:String)


}
