package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

/**
 *
 * The FeedViewmodel handles the information for the Feedfragment.
 * @param repository: The repository through which the public recipes are handled.
 */
class FeedViewModel(repository: PublicRecipeRepository): ViewModel() {


    var pageNumber:Int = 1

    var recipes = repository.getPublicRecipes(pageNumber)

}
