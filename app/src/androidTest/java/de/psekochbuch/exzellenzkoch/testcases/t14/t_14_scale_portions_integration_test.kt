package de.psekochbuch.exzellenzkoch.testcases.t14

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
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch

class t_14_scale_portions_integration_test{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    @Test
    fun test(){
        var authResult: AuthenticationResult? = null
        val repo = PublicRecipeRepositoryImp.getInstance()
        val userRepo = UserRepositoryImp.getInstance()
        FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
        val latch = CountDownLatch(1)
        val randomStr=getRandomString(12)

        AuthentificationImpl.register("extraTestServer${randomStr}@test.de","123456","random${randomStr}"){ a, b->
            authResult = b
            repo.setToken(a)
            userRepo.setToken(a)
            latch.countDown()
        }

        latch.await()

        assertEquals(authResult, AuthenticationResult.REGISTRATIONSUCCESS)

        runBlocking {
            userRepo.addUser("random${randomStr}")
        }

        val recipe = PublicRecipe(0,"titel","",
            listOf(IngredientChapter(2, "chapter",listOf(IngredientAmount("ingedient",2.0,"Einheit")))),
            listOf("tag1","tag2"),"So macht man es","",1,2, User("random${randomStr}"),
            Date(System.currentTimeMillis()),1,0.0)

        var id = 0

        runBlocking { id = repo.publishRecipe(recipe) }

        val fromrepo = repo.getPublicRecipe(id).blockingObserve()!!

        fromrepo.scaleUP()

        val scaledrecipe = PublicRecipe(0,"titel","",
            listOf(IngredientChapter(2, "chapter",listOf(IngredientAmount("ingedient",4.0,"Einheit")))),
            listOf("tag1","tag2"),"So macht man es","",1,2, User("random${randomStr}"),
            Date(System.currentTimeMillis()),2,0.0)

        assertEquals(fromrepo,scaledrecipe)

        fromrepo.scaleDown()

        assertEquals(fromrepo,recipe)

        AuthentificationImpl.userDelete()
    }
    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        observeForever{
            value = it
            latch.countDown()
        }

        latch.await()
        return value
    }
}

