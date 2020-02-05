package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


/**
 * The repository implementation has to fill the interfaces methods
 */
class PublicRecipeRepositoryImp : PublicRecipeRepository {

    val token = null
    //TODO token von Authentification Interface bekommen.

    val retrofit: PublicRecipeApi =
        ApiServiceBuilder(token).createApi(PublicRecipeApi::class.java) as PublicRecipeApi

    override suspend fun removePublicRecipe(recipe: PublicRecipe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Throws
    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {

        val dto = retrofit.getRecipe(1)
        val recipe1 = PublicRecipe("Test", ingredients=listOf(), taglist=listOf("sauer,salzig"))
        val recipe2 = PublicRecipe("Test", ingredients=listOf(), taglist=listOf("sauer,salzig"))

        val list = listOf(recipe1, recipe2)
        val ld = MutableLiveData<List<PublicRecipe>>().apply { list }
        return ld
    }
}




