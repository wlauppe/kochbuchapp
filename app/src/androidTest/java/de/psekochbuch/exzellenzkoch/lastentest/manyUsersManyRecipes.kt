package de.psekochbuch.exzellenzkoch.lastentest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class manyUsersManyRecipes{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun test(){
        val testId = "Testrunde2"
        var notpublishedRecipes = ""
        for (user:Int in 1..2){
            var authResult: AuthenticationResult? = null
            val repo = PublicRecipeRepositoryImp.getInstance()
            FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
            val latch = CountDownLatch(1)
            val userId = testId + "mailadresse@test.de"
            val pw = "123456"
            AuthentificationImpl.register(userId,pw,userId,{
                t,r->
                    authResult = r
                    repo.setToken(t)
                    latch.countDown()
            })



            latch.await()

            Assert.assertEquals(authResult, AuthenticationResult.REGISTRATIONSUCCESS)

            notpublishedRecipes += "user: " + AuthentificationImpl.getUserId() + ""

            val fromrepo = repo.getPublicRecipe(2).blockingObserve()!!
            fromrepo.user = User(AuthentificationImpl.getUserId())



            for (i in 0 .. 35){
                fromrepo.title = testId + ",user:" + user.toString() + ",rezept:" + i.toString()

                runBlocking {
                    try {
                        repo.publishRecipe(fromrepo)
                    } catch (e: Exception) {
                        notpublishedRecipes += i.toString() + " "
                    }
                }

            }
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