package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository

class PrivateRecipeFakeRepositoryImp : PrivateRecipeRepository {
    override fun getPrivateRecipes(): LiveData<List<PrivateRecipe>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPrivateRecipe(id: Int): LiveData<PrivateRecipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deletePrivateRecipe(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updatePrivateRecipe(id: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecipe(id: String): LiveData<PrivateRecipe> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PrivateRecipeRepository? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: PrivateRecipeFakeRepositoryImp().also { instance = it }
        }
    }
}
