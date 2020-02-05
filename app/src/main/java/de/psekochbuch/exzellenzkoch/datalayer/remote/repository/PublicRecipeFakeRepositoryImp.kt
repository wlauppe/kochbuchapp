package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


class PublicRecipeFakeRepositoryImp() : PublicRecipeRepository {
    suspend override  fun removePublicRecipe(recipe: PublicRecipe) {

    }

    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {
        val recipe1 = PublicRecipe(1,"trockener Sandkuchen")
        val recipe2 = PublicRecipe(2,"Quiche", imgUrl = "file:///android_asset/quiche.png")
        val recipe3 = PublicRecipe(3,"Bratapfel", imgUrl = "file:///android_asset/bratapfel.png")
        val list = listOf(recipe1,recipe2, recipe3)

        val ld = MutableLiveData <List<PublicRecipe>>().apply { list }
        return ld
    }

   /* companion object {

        // For Singleton instantiation
        @Volatile private var instance: PublicRecipeRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: PublicRecipeFakeRepositoryImp().also { instance = it }
            }
    } */
}