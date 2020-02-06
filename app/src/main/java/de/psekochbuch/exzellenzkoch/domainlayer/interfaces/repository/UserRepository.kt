package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

interface UserRepository {

    /**
     * Return the user (needed to load users from recipes eg)
     */

    fun getUsers(): LiveData<List<User>>

    suspend fun reportUser(user: User)

    @Throws
    suspend fun deleteUser(userId : String)

    @Throws
    suspend fun addUser(userId : String)

    @Throws
    suspend fun updateUser(user : User)
}