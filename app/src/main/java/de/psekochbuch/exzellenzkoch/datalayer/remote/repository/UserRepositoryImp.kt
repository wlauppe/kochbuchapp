package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.UserApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.PublicRecipeDtoEntityMapper
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.UserDtoEntityMapper
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.errors.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class UserRepositoryImp : UserRepository  {
    val userMapper = UserDtoEntityMapper()
    private val TAG = "UserRealImp"
    private var token :String? = null

    var userApiService: UserApi =
        ApiServiceBuilder(token).createApi(UserApi::class.java) as UserApi

    val fake = UserFakeRepositoryImp()

    override fun getReportedUsers(): LiveData<List<User>> {
        //TODO umstellen
        return fake.getUsers()

    }

    override fun getUser(userId: String): LiveData<User> {


            Log.w(TAG, "getPublicRecipes() wird aufgerufen")
            val lData = liveData(Dispatchers.IO, 1000) {
                Log.w(TAG, "jetzt bin ich im Coroutine Scope")
                try {
                    val dto =
                        userApiService.getUser(userId)
                    dto?.let {
                        val entity = UserDtoEntityMapper().toEntity(dto)
                        emit(entity)
                    }
                }
                catch(error : Throwable) {
                    emit(User( userId= "Error Fetching User! with Id=$userId", imgUrl = "file:///android_asset/exampleimages/error.png"))
                }
            }
            return lData
        }




    override suspend fun checkUser(userId: String): User? {
        val user = userApiService.checkUser(userId)
        if(user != null) {
            return UserDtoEntityMapper().toEntity(user)
        }
        return null
    }

    override suspend fun deleteUser(userId: String) {
        try{
            coroutineScope{userApiService.deleteUser(userId)}
        } catch (error: Throwable) {
            throw NetworkError("Unable to delete User with userId " + userId, error)
        }
    }

    override suspend fun addUser(userId: String) :String {
        return userApiService.addUser(userId).customToken
    }

    override suspend fun updateUser(user: User) {
        try{
            coroutineScope{userApiService.updateUser(user.userId, userMapper.toDto(user))}
        } catch (error: Throwable) {
            throw NetworkError("Unable to update user", error)
        }
    }


    override suspend fun reportUser(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun unreportUser(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: UserRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserRepositoryImp().also { instance = it }
            }
    }

    override fun setToken(token:String)
    {
        this.token = token
        userApiService = ApiServiceBuilder(token).createApi(UserApi::class.java) as UserApi
    }

}
