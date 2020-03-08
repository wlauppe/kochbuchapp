package de.psekochbuch.exzellenzkoch.lastentest

import android.util.Log
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.FileApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import de.psekochbuch.exzellenzkoch.BuildConfig
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.FileDto
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test

class ImageApiTest {
    val tag = "ImageApiTest"
    val imagePath = "/storage/emulated/0/recipe_pictures"
    var imgUrlList = mutableListOf<String>()
    var token = ""
    val imageCount = 100
    val userCount = 2

    private fun initalizeImgUrlList() {
        File(imagePath).walk().forEach {
            if (it.isFile) {
                imgUrlList.add(it.absolutePath)
            }

        }
    }

    private fun getNextImgUrl(): String {
        val url = imgUrlList.get(0)
        return url
    }

    private fun getRandomString(length: Int): String {
        val allowedChars = "abcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun createRandomUser(): User {
        val userId = "testuser_${getRandomString(12)}"
        val user = User(
            userId,
            "file:///android_asset/exampleimages/checkmark.png",
            "Ich bin ein Testuser für den Lastentest"
        )
        return user
    }

    @Ignore
    @Test
    fun testApi(){
        for (i in 1..userCount) {
            initalizeImgUrlList()
            var authResult: AuthenticationResult? = null
            val repo = PublicRecipeRepositoryImp.getInstance()
            val userRepo = UserRepositoryImp.getInstance()
            FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
            val latch = CountDownLatch(1)

            val user = createRandomUser()
            Log.i(tag, "user created with id: ${user.userId}")
            val email = "${user.userId}@test.de"
            val password = "123456"



            AuthentificationImpl.register(email, password, user.userId) { a, b ->
                authResult = b
                repo.setToken(a)
                userRepo.setToken(a)
                token = a!!
                latch.countDown()
            }

            latch.await(10, TimeUnit.SECONDS)

            var fileApiService: FileApi =
                ApiServiceBuilder(token).createApi(FileApi::class.java) as FileApi

            for (i in 1..imageCount) {
                val imgUrl = getNextImgUrl()
                //First upload the Image.
                if (imgUrl != "") {
                    val file: File = File(imgUrl)
                    val ex = file.exists()
                    val body = RequestBody.create(MediaType.parse("*/*"), file)
                    val multi = MultipartBody.Part.createFormData("file", file.name, body)
                    val requestFile: RequestBody =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    lateinit var response: FileDto
                    runBlocking {
                        response = fileApiService.addImage(multi)
                    }
                    //TODO Baseurl hinzufügen eventuell in den Mapper.
                    val remoteUrl = response.filePath
                    //speichere filepath in recipe
                    //TODO Muss noch Mapper schreiben, dass URL gemappt wird.
                    var newimgUrl = BuildConfig.IMG_PREFIX + remoteUrl
                    Log.i(tag, "neue url wäre $newimgUrl")
                }
            }
        }


    }
}