package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User


class AdminViewModel : ViewModel() {


    var fakreRepo: PublicRecipeFakeRepository = PublicRecipeFakeRepository()
    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */


    var recipes : LiveData<List<PublicRecipe>> = fakreRepo.getPublicRecipes()

    var users : LiveData<List<User>> = fakreRepo.getUsers()


    /**
     *
     */
    fun deleteRecipe(recipeID: Int) {

    }

    fun deleteUser(userID: String) {

    }

    fun spareUser(id : String?){

    }
    fun spareRecipe(id: Int?){

    }


}
