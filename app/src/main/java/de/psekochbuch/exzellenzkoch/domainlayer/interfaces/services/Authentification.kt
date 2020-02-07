package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services

import android.util.JsonToken
import de.psekochbuch.exzellenzkoch.userinterfacelayer.AuthenticationResult

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


    fun isAdmin(userId : String) : Boolean


    /**
     * Methods still needed:
     *
     * deleteUser()
     * editUser()
     *editPassword()
     */
}