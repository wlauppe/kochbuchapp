package de.psekochbuch.exzellenzkoch.lastentest

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
import java.util.*
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
        AuthentificationImpl.register("extraTestServer300@test.de","123456",""){ a, b->
            authResult = b
            repo.setToken(a)
            latch.countDown()
            }

        latch.await(2, TimeUnit.SECONDS)

        assertEquals(authResult, AuthenticationResult.REGISTRATIONSUCCESS)

        var fromrepo = repo.getPublicRecipe(2).blockingObserve()!!

        fromrepo.imgUrl = ""

        fromrepo = PublicRecipe(1,"titel","ingredients",
            listOf(IngredientChapter(2, "chapter",listOf(IngredientAmount("ingedient",2.0,"Einheit")))),
            listOf("tag1","tag2"),"So macht man es","",1,2,User(""),
            Date(System.currentTimeMillis()),3,4.0)
        fromrepo.user = User(AuthentificationImpl.getUserId())

        val titlewithoutnumber = "Testrunde 2, rezept: "

        try {
            for (i in 0 .. 3){
                fromrepo.title = titlewithoutnumber + i.toString()
                runBlocking { repo.publishRecipe(fromrepo) }
            }
        } catch (e:Exception){}
        finally {
            AuthentificationImpl.userDelete()
        }
    }
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