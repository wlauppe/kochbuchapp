package de.psekochbuch.exzellenzkoch.testcases.t7

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class t_7_7_unique_user_id_integration_test{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    /**
     * Hier wird ein nutzer mit einer zufälligen id genertiert und dann diese von der Authenfizierung abgefragt und überprüft ob diese gleich sind
     */
    @Test
    fun test(){
        var authResult: AuthenticationResult? = null
        val repo = PublicRecipeRepositoryImp.getInstance()
        val userRepo = UserRepositoryImp.getInstance()
        FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
        val latch = CountDownLatch(1)
        val randomStr=getRandomString(12)

        val userid = "random${randomStr}"

        AuthentificationImpl.register("extraTestServer${randomStr}@test.de","123456",userid){ a, b->
            authResult = b
            repo.setToken(a)
            userRepo.setToken(a)
            latch.countDown()
        }

        latch.await()

        assertEquals(authResult, AuthenticationResult.REGISTRATIONSUCCESS)
        runBlocking {
            val token = userRepo.addUser(userid)
        }
        

        val newuserid = "random${getRandomString(12)}"

        runBlocking {
            userRepo.updateUser(userid, User(newuserid,"",""))
        }

        val fromrepo = AuthentificationImpl.getUserId()

        assertEquals(AuthentificationImpl.getUserId(),newuserid)

        AuthentificationImpl.userDelete()
    }
}