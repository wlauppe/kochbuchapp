package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository


class AdminViewModel : ViewModel() {

    var userRepo: UserRepository = UserFakeRepositoryImp()
    var publicRecipeRepo : PublicRecipeRepository = PublicRecipeFakeRepositoryImp()
    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */


    var recipes : LiveData<List<PublicRecipe>> = publicRecipeRepo.getPublicRecipes()

    var users : LiveData<List<User>> = userRepo.getUsers()


    fun deleteRecipe(recipeID: Int) {

    }

    fun deleteUser(userID: String) {

    }

    fun spareUser(id : String?){

    }
    fun spareRecipe(id: Int?){

    }


}
