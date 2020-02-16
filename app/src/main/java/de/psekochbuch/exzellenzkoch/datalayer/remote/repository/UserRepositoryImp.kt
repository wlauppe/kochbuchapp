package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.IMG_PREFIX
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.AdminApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.FileApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.UserApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.mapper.UserDtoEntityMapper
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UserRepositoryImp : UserRepository {
    val userMapper = UserDtoEntityMapper()
    private val TAG = "UserRealImp"
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
        val lData = liveData(Dispatchers.IO, 1000) {
            Log.w(TAG, "jetzt bin ich im Coroutine Scope")
            try {
                val dto =
                    userApiService.getUser(userId)
                dto?.let {
                    val entity = UserDtoEntityMapper().toEntity(dto)
                    emit(entity)
                }
            } catch (error: Throwable) {
                emit(
                    User(
                        userId = "Error Fetching User! with Id=$userId",
                        imgUrl = "file:///android_asset/exampleimages/error.png"
                    )
                )
            }
        }
        return lData
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
        try {
            //First upload the Image.
            val file : File = File(user.imgUrl)
            val body = RequestBody.create(MediaType.parse("image/*"), file)
            val multi = MultipartBody.Part.createFormData("file", file.name, body)
            val requestFile : RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val response = fileApiService.addImage(multi)
            //TODO Baseurl hinzufügen eventuell in den Mapper.
            val remoteUrl = response.filePath
            //speichere filepath in recipe
            //TODO Muss noch Mapper schreiben, dass URL gemappt wird.
            user.imgUrl= IMG_PREFIX +remoteUrl
            val returnDto = userApiService.updateUser(oldUserId,userMapper.toDto(user))
        } catch (error: Throwable) {
          //  throw NetworkError("Unable to update user", error)
        }
    }


    override suspend fun reportUser(userId: String) {
        try {
                userApiService.reportUser(userId)
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
