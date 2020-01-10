package de.psekochbuch.exzellenzkoch.remote.api

import de.psekochbuch.exzellenzkoch.remote.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi
{
    @GET("/api/user")
    fun getUser(@Query("userId") userId:String) : Call<User>
}