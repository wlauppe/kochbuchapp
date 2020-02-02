package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository

class AdminViewModel : ViewModel() {

    var mreporecipe = PublicRecipeFakeRepository()

    var recipes: LiveData<List<PublicRecipe>> = mreporecipe.getPublicRecipes()

    var profiles: LiveData<List<User>>? = null


    fun deleteRecipe(recipeID: Int){

    }

    fun deleteUser(userID: String){

    }
}
