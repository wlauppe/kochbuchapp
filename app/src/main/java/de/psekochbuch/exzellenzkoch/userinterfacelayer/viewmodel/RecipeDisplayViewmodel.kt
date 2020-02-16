package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.RecipeDisplayFragment
import kotlinx.coroutines.launch
import java.util.*

class RecipeDisplayViewmodel(repository:PublicRecipeRepository) : ViewModel() {
    var Tag = "RecipeDisplayViewmodel"

    val repository=repository

    var recipe = MutableLiveData(PublicRecipe())

    //Get PublicRecipeFrom Repo and set it as the current Recipe
    fun setRecipeByID(id:Int?){
        Log.i(Tag, "SetRecipe BY ID $id")
        if(id == null){
            return
        }
        recipe = repository.getPublicRecipe(id) as MutableLiveData<PublicRecipe>
    }

//Wunschkriterium
    fun addToFavourites() {
        //repo.addToFacourites(id)
    }


}