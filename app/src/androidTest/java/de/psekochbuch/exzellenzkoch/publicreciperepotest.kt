package de.psekochbuch.exzellenzkoch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
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


        val recipe = PublicRecipe(1,"titeellll","soooooo",listOf(),listOf(),"prep","",3,3, User(AuthentificationImpl.getUserId()))

        runBlocking { repo.publishRecipe(recipe)}

    }
}
