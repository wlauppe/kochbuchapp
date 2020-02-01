package de.psekochbuch.exzellenzkoch.datalayer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


class PublicRecipeFakeRepository : PublicRecipeRepository {
    override suspend fun removePublicRecipe(recipe: PublicRecipe) {

    }

    override fun getPublicRecipes(): LiveData<List<PublicRecipe>> {
        val recipe1 = PublicRecipe(listOf(),"trockener Sandkuchen", 5.0, "Backe backe Kuchen", listOf("trocken","kuchen","ungeniessbar"),5, 2,null, 5);
        //val recipe2 = PublicRecipe(listOf(), "ganz trockener Sandkuchen", 2.0, "Backe backe Kuchen",tags=listOf("trocken","kuchen","ungeniessbar"))
        val recipe2= recipe1

        val list = listOf(recipe1,recipe2)

        val ld = MutableLiveData <List<PublicRecipe>>().apply { list }
        return ld
    }
}