package de.psekochbuch.exzellenzkoch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class publicreciperepotest{
    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun getPublicRecipe() {
        var authResult: AuthenticationResult? = null
        val repo = PublicRecipeRepositoryImp.getInstance()
        FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
        val latch = CountDownLatch(1)
        AuthentificationImpl.login("test@test.de","123456",{ a, b->
            authResult = b
            repo.setToken(a)
            latch.countDown()
            })

        latch.await(2, TimeUnit.SECONDS)

        assertEquals(authResult, AuthenticationResult.LOGINSUCCESS)


        val recipe = PublicRecipe(123455324,"titeellll","soooooo",listOf(IngredientChapter(1,"zutagen",listOf(
            IngredientAmount("halal",2.0,"schweine")
        ))),listOf("tag1"),"prep","jf",3,3, User(AuthentificationImpl.getUserId()))

        val fromrepo = repo.getPublicRecipe(1).blockingObserve()
        1
        runBlocking { repo.publishRecipe(recipe)}

    }
}

private fun <T> LiveData<T>.blockingObserve(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    observeForever{
        value = it
        latch.countDown()
    }

    latch.await(2, TimeUnit.SECONDS)
    return value
}