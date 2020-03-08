package de.psekochbuch.exzellenzkoch.testcases.t7

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
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

    private fun createRandomUser(): User {
        val userId = "testuser_${getRandomString(12)}"
        val user = User(
            userId,
            "file:///android_asset/exampleimages/checkmark.png",
            "Ich bin ein Testuser für den Lastentest"
        )
        return user
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

        val user = createRandomUser()
        Log.i("uniqueuserintegrationtest", "user created with id: ${user.userId}")
        val email = "${user.userId}@test.de"
        val password = "123456"



        AuthentificationImpl.register(email, password, user.userId) { a, b ->
            authResult = b
            repo.setToken(a)
            userRepo.setToken(a)
            latch.countDown()
        }

        latch.await(10, TimeUnit.SECONDS)

        Assert.assertEquals(authResult, AuthenticationResult.REGISTRATIONSUCCESS)
        runBlocking {
            val token = userRepo.addUser(user.userId)
        }

        user.description = "blablabla"
        runBlocking {
            userRepo.updateUser(user.userId, user)
        }

        val fromrepo = userRepo.getUser(user.userId).blockingObserve()!!

        assertEquals(fromrepo.description,user.description)



        AuthentificationImpl.userDelete()
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