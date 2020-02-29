package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.EspressoIdlingResource
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.PAGE_SIZE
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

/**
 *
 * The FeedViewmodel handles the information for the Feedfragment.
 * @param repository: The repository through which the public recipes are handled.
 */
class FeedViewModel(val repository: PublicRecipeRepository): ViewModel() {
    var pageNumber:Int = 1
    var recipePage : LiveData<List<PublicRecipe>> = MutableLiveData(emptyList())
    var recipes : LiveData<List<PublicRecipe>> = repository.getPublicRecipes(pageNumber)

    init {
        recipePage.observeForever({ recipePageList: List<PublicRecipe> ->
            recipes = liveData {
                if (recipes.value.isNullOrEmpty()) {
                    emit(recipePageList)
                } else {
                    val oldList = recipes.value!!
                    val joined = ArrayList<PublicRecipe>()
                    joined.addAll(oldList)
                    joined.addAll(recipePageList)
                    emit(joined.toList())
                }
            }
        })
        recipePage = repository.getPublicRecipes(pageNumber)
    }

    fun loadNextPage() {
        recipePage = repository.getPublicRecipes(pageNumber)
    }
}
