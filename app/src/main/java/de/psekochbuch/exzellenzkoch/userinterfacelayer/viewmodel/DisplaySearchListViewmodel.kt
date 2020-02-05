package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.InjectorUtils.getPublicRecipeRepository
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp

import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

class DisplaySearchListViewmodel : ViewModel() {

   // var repo = PublicRecipeRepositoryImp()

    //var fakreRepo: PublicRecipeFakeRepository = PublicRecipeFakeRepository()
    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */

    val repository= PublicRecipeFakeRepositoryImp()

    var recipes : LiveData<List<PublicRecipe>> = repository.getPublicRecipes()
    





   /* var first  = "Piroggen"
    var second = "Pommes"
    var third = "Käse"
    var fourth = "Döner"
    var list = listOf<String>(first,second,third,fourth)

   val items: MutableLiveData<List<String>> = MutableLiveData(list)
*/



}