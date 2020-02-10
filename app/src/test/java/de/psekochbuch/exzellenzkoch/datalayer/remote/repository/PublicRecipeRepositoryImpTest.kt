package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.TestApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class PublicRecipeRepositoryImpTest{
    @Test
    fun test(){
        val retrofit: PublicRecipeApi =
            ApiServiceBuilder(null).createApi(PublicRecipeApi::class.java) as PublicRecipeApi
        var recipe:PublicRecipeDto
        retrofit.getRecipe(1)


       // assertEquals(call.execute().body()!!.creationDate, "test")


    }

}