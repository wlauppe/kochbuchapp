package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.BuildConfig
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.AdminApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.FileApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.UserApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.FileDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.UserDtoEntityMapper
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.errors.NetworkError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserRepositoryImp : UserRepository {
    val userMapper = UserDtoEntityMapper()
    private val TAG = "UserRealImp"
    private var cache : User? = null
    private var token :String? = null

    var userApiService: UserApi =
        ApiServiceBuilder(token).createApi(UserApi::class.java) as UserApi
    var adminApiService: AdminApi =
        ApiServiceBuilder(token).createApi(AdminApi::class.java) as AdminApi

    var fileApiService: FileApi =
        ApiServiceBuilder(token).createApi(FileApi::class.java) as FileApi

    val fake = UserFakeRepositoryImp()

    override fun getReportedUsers(): LiveData<List<User>> {

        val lData = liveData(Dispatchers.IO, 1000) {
              Log.w(TAG, "jetzt bin ich im Coroutine Scope")
            try {
                val dtoList =
                    adminApiService.getReportedUsers(1, 100)
                dtoList.let {
                    val entityList = UserDtoEntityMapper().toListEntity(dtoList)
                    emit(entityList)
                }
            } catch (error: Throwable) {
                val list=listOf(
                        User(
                            userId = "No reported Users found",
                            imgUrl = "file:///android_asset/exampleimages/checkmark.png"
                        ))
                emit(list)
          }



        }
        return lData

    }

    override fun getUser(userId: String): LiveData<User> {


        Log.w(TAG, "getUser() wird aufgerufen")
        if (cache != null && cache!!.userId == userId) {
            return liveData<User> { emit(cache!!)}
        }
        else {
            val lData = liveData(Dispatchers.IO, 4000) {
                Log.w(TAG, "jetzt bin ich im Coroutine Scope")
                try {
                    Log.w(TAG, "vor dem getUser")
                    val dto =
                        userApiService.getUser(userId)
                    dto?.let {
                        Log.w(TAG, "vor dem Mapper")
                        val entity = UserDtoEntityMapper().toEntity(dto)
                        emit(entity)
                    }
                } catch (error: Throwable) {
                    emit(
                        User(
                            userId = "Error Fetching User! with Id = $userId",
                            imgUrl = "file:///android_asset/exampleimages/error.png"
                        )
                    )
                }
            }
            return lData
        }
    }

    override suspend fun checkIsUserIdRegistered(userId: String): User? {
        val user = userApiService.checkUser(userId)
        return UserDtoEntityMapper().toEntity(user)
    }


    override suspend fun deleteUser(userId: String) {
        try {
            coroutineScope { userApiService.deleteUser(userId) }
        } catch (error: Throwable) {
           // throw NetworkError("Unable to delete User with userId " + userId, error)
        }
    }

    override suspend fun addUser(userId: String): String {
        return userApiService.addUser(userId).customToken
    }

    override suspend fun updateUser(oldUserId: String, user: User) {
        var response: FileDto? =null
        val remoteUrl : String

        cache = user
        val file: File = File(user.imgUrl)
        Log.i(TAG,"ImgUrl is ${user.imgUrl}")
        val ex = file.exists()
        try {
             //Nur versuchen Bild hochzuladen wenn es lokal auf dem Handy liegt.
            //ansonsten wenn es zum Beispiel schon ein http://Link ist nicht.
            if(ex) {
                val body = RequestBody.create(MediaType.parse("*/*"), file)
                val multi = MultipartBody.Part.createFormData("file", file.name, body)
                val requestFile: RequestBody =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file)
                response = fileApiService.addImage(multi)
            }
        }
        catch(error : Throwable) {
            throw NetworkError("Unable to publish recipe image", error)
        }
        try {
            if (response!=null) {
                //setze url nur, wenn ich network bild speichern durchgeführt habe
                remoteUrl = response.filePath
                user.imgUrl= BuildConfig.IMG_PREFIX +remoteUrl
            }
            //speichere filepath in recipe
            Log.w(TAG,"update user, $oldUserId imageUrl =${user.imgUrl}")
            userApiService.updateUser(oldUserId,userMapper.toDto(user))
        } catch (error: Throwable) {
            Log.e(TAG,"update User, hat nicht Funktioniert error: error.message")
          //  throw NetworkError("Unable to update user", error)
        }
    }


    override suspend fun reportUser(userId: String) {
        try {
               coroutineScope { userApiService.reportUser(userId)}
        } catch (error: Throwable) {
           // throw NetworkError("Unable to update user", error)
        }
    }

    override suspend fun unreportUser(userId: String) {
        try {
                adminApiService.deReportUser(userId)
        } catch (error: Throwable) {
           // throw NetworkError("Unable to update user", error)
        }
    }

    override fun setToken(token: String?) {
        this.token = token
        userApiService = ApiServiceBuilder(token).createApi(UserApi::class.java) as UserApi
        adminApiService = ApiServiceBuilder(token).createApi(AdminApi::class.java) as AdminApi
        fileApiService = ApiServiceBuilder(token).createApi(FileApi::class.java) as FileApi
    }

    override fun isTokenSet(): Boolean {
        if(token != null)
        {
            return true
        }
        return false
    }


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserRepositoryImp().also { instance = it }
            }
    }
}
