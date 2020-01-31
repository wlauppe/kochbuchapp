package de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.repository

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository

class PrivateRecipeRepositoryImplementation : PrivateRecipeRepository {


    override fun getPrivateRecipes(): LiveData<List<PrivateRecipe>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deletePrivateRecipe(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecipe(id: String): LiveData<PrivateRecipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}