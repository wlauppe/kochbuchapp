package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services

import android.util.JsonToken


interface Authentification {

    /**
     * Creates a new user account via firebase, using the email and password provided
     */
    suspend fun register(email:String, password:String)

    /**
     * Logs the user with the matching parameters in
     */
    suspend fun login(email:String, password:String)

    /**
     * Logs a logged in user out. Needs isLoggedIn()
     */
    suspend fun logout()

    /**
     * Checks if there's a logged-in user currently
     */
    fun isLoggedIn():Boolean

    /**
     * Returns the JSOn Token if the user is logged in.
     * Throws exception otherwise.
     */
    suspend fun getToken():JsonToken

    /**
     * Set a new password for the current user
     */
    suspend fun editPassword()

    /**
     * Delete all data of the user
     */
    suspend fun deleteUser()

    fun isAdmin(userId : String) : Boolean


    /**
     * The user can edit it's userID and description
     */
    suspend fun editUser(userId:String, description:String)

}