package de.psekochbuch.exzellenzkoch.domainlayer.api

import de.psekochbuch.exzellenzkoch.domainlayer.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi
{
    @GET("/api/user")
    fun getUser(@Query("userId") userId:String) : Call<User>
}