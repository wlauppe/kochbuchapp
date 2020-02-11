package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.UserApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.PublicRecipeDtoEntityMapper
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.CustomTokenDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.UserDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.EntityMapper
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.UserDtoEntityMapper
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.errors.NetworkError
import kotlinx.coroutines.coroutineScope

class UserRepositoryImp : UserRepository  {
    val userMapper = UserDtoEntityMapper()

    private var token :String? = null

    var retrofit: UserApi =
        ApiServiceBuilder(token).createApi(UserApi::class.java) as UserApi

    override fun getUsers(): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUsers(userIdPraefix: String): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(userId: String): LiveData<User> {
        try{
            return userMapper.toLiveEntity(retrofit.getUser(userId).body()!!)
        } catch (error: Throwable) {
        throw NetworkError("Unable to delete User with userId " + userId, error)
        }
    }

    override suspend fun checkUser(userId: String): User? {
        val user = retrofit.checkUser(userId)
        if(user != null) {
            return UserDtoEntityMapper().toEntity(user)
        }
        return null
    }

    override suspend fun deleteUser(userId: String) {
        try{
            coroutineScope{retrofit.deleteUser(userId)}
        } catch (error: Throwable) {
            throw NetworkError("Unable to delete User with userId " + userId, error)
        }
    }

    override suspend fun addUser(userId: String) :String {
        return retrofit.addUser(userId).customToken
    }

    override suspend fun updateUser(user: User) {
        try{
            coroutineScope{retrofit.updateUser(user.userId, userMapper.toDto(user))}
        } catch (error: Throwable) {
            throw NetworkError("Unable to update user", error)
        }
    }

    override fun getReportedUsers(): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun reportUser(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun unreportUser(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    public fun setToken(token:String)
    {
        this.token = token
        retrofit = ApiServiceBuilder(token).createApi(UserApi::class.java) as UserApi
    }

}
