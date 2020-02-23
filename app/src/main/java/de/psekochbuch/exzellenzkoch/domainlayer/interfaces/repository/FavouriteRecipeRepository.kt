package de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

interface FavouriteRecipeRepository {
    /**
     * returns a list of all favourite recipes
     */
    fun getFavourites():LiveData<List<PublicRecipe>>

    /**
     * returns the recipe with that id
     */
    fun getFavourite(id:Int):LiveData<PublicRecipe>

    /**
     * removes the recipe with that id
     */
    fun removeFavourite(id:Int)

    /**
     * inserts a public recipe into the favourites
     */
    fun insertFavourite(recipe: PublicRecipe)
}