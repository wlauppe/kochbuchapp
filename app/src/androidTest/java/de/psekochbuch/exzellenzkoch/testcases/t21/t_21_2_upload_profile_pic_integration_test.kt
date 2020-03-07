package de.psekochbuch.exzellenzkoch.testcases.t21

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch

class t_21_2_upload_profile_pic_integration_test{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    fun getRandomString(length: Int) : String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    @Test
    fun test() {
        var authResult: AuthenticationResult? = null
        val repo = PublicRecipeRepositoryImp.getInstance()
        val userRepo = UserRepositoryImp.getInstance()
        FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
        val latch = CountDownLatch(1)
        val randomStr = getRandomString(12)

        AuthentificationImpl.register(
            "extraTestServer${randomStr}@test.de",
            "123456",
            "random${randomStr}"
        ) { a, b ->
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

        val newUser = User("random${randomStr}", "https://s.gravatar.com/avatar/f849c680f420d89b5b0b49979d1df5ec?s=80", "Toast")

        runBlocking {
            userRepo.updateUser("random${randomStr}", newUser)
        }

        val fromrepo = userRepo.getUser("random${randomStr}").blockingObserve()!!

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

    latch.await()
    return value
}