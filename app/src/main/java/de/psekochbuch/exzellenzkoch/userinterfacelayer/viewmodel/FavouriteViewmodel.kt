package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.FavouriteRecipeRepository

class FavouriteViewmodel(repository : FavouriteRecipeRepository) : ViewModel(){

    val repo = repository
    //var recipes = repository.getFavourites()
    var recipes : LiveData<List<PrivateRecipe>> = MutableLiveData(emptyList())


    fun deleteRecipeFromFavourites(id: Int){
        //TODO Delete from Favourites
    }
}