package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class AdminViewModel : ViewModel() {

    var mrepoRecipe = PublicRecipeFakeRepository()

    var recipes:MutableLiveData<List<String>> = mrepoRecipe.getPublicRecipesAsStrings()

    var profiles: LiveData<List<User>>? = null


    fun deleteRecipe(recipeID: Int) {

    }

    fun deleteUser(userID: String) {

    }
}
