package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.repository.PrivateRecipeRepositoryImplementation
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe


class RecipeListViewmodel : ViewModel() {

    var mrepo = PrivateRecipeRepositoryImplementation()

    val fakeRepo : PublicRecipeFakeRepository = PublicRecipeFakeRepository()
   // var recipes: MutableLiveData<List<PrivateRecipe>> = MutableLiveData()

    var recipes : LiveData<List<PublicRecipe>> = fakeRepo.getPublicRecipes()


    fun getNamesFromRecipes(liveData: LiveData<List<PrivateRecipe>>) {
        //TODO

    }

    fun deleteRecipe(id: Int?) {

        //TODO

    }


}