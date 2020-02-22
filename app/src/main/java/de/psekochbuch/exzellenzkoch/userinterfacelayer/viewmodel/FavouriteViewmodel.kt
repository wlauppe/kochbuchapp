package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.FavouritRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

class FavouriteViewmodel(repository : FavouritRecipeRepository) : ViewModel(){

    val repo = repository
    var recipes = repository.getFavourites()


    fun deleteRecipeFromFavourites(id: Int){
        //TODO Delete from Favourites
    }
}