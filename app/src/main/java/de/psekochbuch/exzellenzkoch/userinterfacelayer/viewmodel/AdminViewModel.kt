package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository


class AdminViewModel(publicRecipeRepo : PublicRecipeRepository) : ViewModel() {

    var userRepo: UserRepository = UserFakeRepositoryImp()

    //var publicRecipeRepo : PublicRecipeRepository = PublicRecipeFakeRepositoryImp()
    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */


    var recipes : LiveData<List<PublicRecipe>> = publicRecipeRepo.getPublicRecipes()

    var users : LiveData<List<User>> = userRepo.getUsers()


    /* fun refreshTitle() {


        viewModelScope.launch {
            try {
               // _spinner.value = true
                repository.reportUser()
            } catch (error: TitleRefreshError) {
                _snackBar.value = error.message
            } finally {
               // _spinner.value = false
            }
        }
        */



    fun deleteRecipe(recipeID: Int) {

    }

    fun deleteUser(userID: String) {

    }

    fun unreportUser(id : String?){

    }
    fun unreportRecipe(id: Int?){

    }


}
