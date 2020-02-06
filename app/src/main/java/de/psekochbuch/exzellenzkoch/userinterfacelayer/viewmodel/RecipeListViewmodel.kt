package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


class RecipeListViewmodel(repository: PublicRecipeRepository) : ViewModel() {

    //var mrepo = PrivateRecipeRepositoryImplementation()

    val repo : PublicRecipeRepository = PublicRecipeFakeRepositoryImp()
   // var recipes: MutableLiveData<List<PrivateRecipe>> = MutableLiveData()

    var recipes : LiveData<List<PublicRecipe>> = repo.getPublicRecipes()


    fun getNamesFromRecipes(liveData: LiveData<List<PrivateRecipe>>) {
        //TODO

    }

    fun deleteRecipe(id: Int?) {

        //TODO

    }


}