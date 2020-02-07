package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp

import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

class DisplaySearchListViewmodel : ViewModel() {


    //var fakreRepo: PublicRecipeFakeRepository = PublicRecipeFakeRepository()
    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */

    val repository=PublicRecipeFakeRepositoryImp()

    var recipes : LiveData<List<PublicRecipe>> = repository.getPublicRecipes()


    

    //Versuch


  //  val repo = PublicRecipeRepositoryImp()
//    var recipes = repo.getPublicRecipes()



}