package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
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
import java.util.*

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
    fun testapi(){
        runBlocking {  val retrofit: PublicRecipeApi =
            ApiServiceBuilder(null).createApi(PublicRecipeApi::class.java) as PublicRecipeApi
            var recipe = retrofit.getRecipe(1)



            retrofit.addRecipe(
                PublicRecipeDto(15,"lalali","fdjlks","server","dahin",1,2,"1", "2020-01-13 00:00:00",3,2.0,listOf(),listOf())
            )


            assertEquals(retrofit.getRecipe(1).preparationDescription,"Man nehme " +
                    "die Schaufel und schaufelt den Sand in das Förmchen. " +
                    "Dann drückt man alles fest und dreht das Förmchen auf einen Tisch um." +
                    " Dann hebt man das Förmchen hoch und man hat den Sandkuchen auf dem Tisch liegen.")
        }

    }

    @Test
    fun testpublicrepo(){
        runBlocking { val repo =PublicRecipeRepositoryImp.getInstance()
                    val recipe = repo.getPublicRecipe(1)
            Thread.sleep(1000)

        assertEquals(recipe.value!!.preparation,"lalali")
        }
    }

}