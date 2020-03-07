package de.psekochbuch.exzellenzkoch.lastentest

import android.os.Environment
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class manyUserManyRecipes {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    val recipeCount = 100
    val userCount = 2
    val imagePath = "/storage/emulated/0/recipe_pictures"
    var imgUrlList = mutableListOf<String>()
        //val userCount = 35
    val tag = "manyUsersManyRecipes"




    private fun getRandomString(length: Int): String {
        val allowedChars = "abcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    private fun initalizeImgUrlList() {
        File(imagePath).walk().forEach {
            if(it.isFile) {
                imgUrlList.add(it.absolutePath)
            }

        }
    }

    private fun getNextImgUrl() : String {
        val url = imgUrlList.get(0)
        return url
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

    private fun createRandomRecipe(user: User): PublicRecipe {
        val recipe = PublicRecipe(
            0, "titel", "ingredients",
            listOf(
                IngredientChapter(
                    2,
                    "chapter",
                    listOf(IngredientAmount("ingedient", 2.0, "Einheit"))
                )
            ),
            listOf("tag1", "tag2"), "So macht man es",getNextImgUrl(), 1, 2, user,
            Date(System.currentTimeMillis()), 3, 4.0
        )
        return recipe
    }


    @Test
    fun publishRecipes() {
        initalizeImgUrlList()
        for (i in 1..userCount) {
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
                latch.countDown()
            }

            latch.await(10, TimeUnit.SECONDS)

            assertEquals(authResult, AuthenticationResult.REGISTRATIONSUCCESS)
            runBlocking {
                val token = userRepo.addUser(user.userId)
            }

            //var fromrepo = repo.getPublicRecipe(2).blockingObserve()!!
            //fromrepo.imgUrl = ""

            for (i in 1..recipeCount) {
                val recipe = createRandomRecipe(user)
                //recipe.user = User(AuthentificationImpl.getUserId())
                try {
                    val titlewithoutnumber = "Testrunde 2, rezept: "
                    Log.i(tag, "creating recipe ${i}")
                    recipe.title = titlewithoutnumber + i.toString()
                    runBlocking { repo.publishRecipe(recipe) }

                } catch (e: Error) {
                    Log.w(tag, "publish Recipe, returned Error ${e.message}")
                }
            }

            //finally {
            //    AuthentificationImpl.userDelete()
            //}
        }
    }


    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        observeForever {
            value = it
            latch.countDown()
        }

        latch.await()
        return value
    }

}