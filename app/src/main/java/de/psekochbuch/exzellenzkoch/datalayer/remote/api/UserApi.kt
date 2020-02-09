package de.psekochbuch.exzellenzkoch.datalayer.remote.api

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.UserDto
import retrofit2.Response
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
    @GET("users/{userId}")
    fun getUser(@Path ("userId") userId: String) : Response<LiveData<UserDto>>
}