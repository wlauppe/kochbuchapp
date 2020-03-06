package de.psekochbuch.exzellenzkoch.lastentest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch

class userCreateTest{

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun test(){
        for (user:Int in 1..300) {
            var authResult: AuthenticationResult? = null
            val repo = PublicRecipeRepositoryImp.getInstance()
            FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
            val latch = CountDownLatch(1)
            val userId = "extraTestServer" + user + "@test.de"
            val pw = "123456"
            AuthentificationImpl.register(userId, pw, userId, { t, r ->
                authResult = r
                repo.setToken(t)
                latch.countDown()
            })



            latch.await()

            Assert.assertEquals(authResult, AuthenticationResult.REGISTRATIONSUCCESS)
        }
    }
}