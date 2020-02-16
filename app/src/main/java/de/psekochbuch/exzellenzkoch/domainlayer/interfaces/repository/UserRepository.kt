package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.UserDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

interface UserRepository {

    /**
     * Return the user (needed to load users from recipes eg)
     */


    fun getUser(UserId: String): LiveData<User>

    /** This function tells whether there exists a Registered user in Firebase, if true
     *  it returns the user, otherwise it returns an empty message.
     */
    @Throws
    suspend fun checkIsUserIdRegistered(userId:String): User?

    @Throws
    suspend fun deleteUser(userId : String)

    @Throws
    suspend fun addUser(userId : String): String

    @Throws
    suspend fun updateUser(user : User)

    fun getReportedUsers() : LiveData<List<User>>

    @Throws
    suspend fun reportUser(userId: String)

    @Throws
    suspend fun unreportUser(userId : String)

    fun setToken(token:String?)

}