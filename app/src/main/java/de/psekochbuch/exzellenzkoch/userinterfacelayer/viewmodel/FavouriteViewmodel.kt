package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.FavouriteRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

class FavouriteViewmodel(repository : FavouriteRecipeRepository, publicrepo: PublicRecipeRepository) : ViewModel(){

    val repo = repository
    val publicRecipeRepository = publicrepo
    //var recipes = repository.getFavourites()
    var recipes : LiveData<List<PublicRecipe>> = MutableLiveData(emptyList())


    fun deleteRecipeFromFavourites(id: Int){
        repo.removeFavourite(id)
    }
}