package de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations

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

    val retrofit : PublicRecipeApi = ApiServiceBuilder(null).createApi(PublicRecipeApi::class.java) as PublicRecipeApi


    override suspend fun removePublicRecipe(recipe: PublicRecipe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Throws
    override  fun getPublicRecipes(): LiveData<List<PublicRecipe>> {
        val dto = retrofit.getRecipe(1)
        val recipe1 = PublicRecipe(listOf(),dto.value!!.title, 5.0, "Backe backe Kuchen", listOf("trocken","kuchen","ungeniessbar"),5, 2,null, 5);
        val recipe2= recipe1

        val list = listOf(recipe1,recipe2)
        val ld = MutableLiveData <List<PublicRecipe>>().apply { list }
        return ld
    }
}




