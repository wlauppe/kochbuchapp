package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.TestApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PublicRecipeApiTest{
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

 @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    @Test
    fun test()  = runBlocking{
        launch(Dispatchers.Main) {
            val retrofit: PublicRecipeApi =
                ApiServiceBuilder(null).createApi(PublicRecipeApi::class.java) as PublicRecipeApi
            var recipe: PublicRecipeDto
            retrofit.getRecipe(1)
        }

       // assertEquals(call.execute().body()!!.creationDate, "test")


    }

}