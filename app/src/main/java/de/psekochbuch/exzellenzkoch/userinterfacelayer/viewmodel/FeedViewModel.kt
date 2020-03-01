package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.util.Log
import androidx.lifecycle.*
import de.psekochbuch.exzellenzkoch.PAGE_SIZE
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.launch

/**
 *
 * The FeedViewmodel handles the information for the Feedfragment.
 * @param repository: The repository through which the public recipes are handled.
 */
class FeedViewModel(val repository: PublicRecipeRepository): ViewModel() {
    var pageNumber:Int = 1
    var recipePage : MutableLiveData<List<PublicRecipe>> = MutableLiveData(emptyList())
    var recipes : MutableLiveData<List<PublicRecipe>> = MutableLiveData(emptyList())

    init {
        recipePage.observeForever { recipePageList: List<PublicRecipe> ->
            // set recipe list value
                if (recipes.value.isNullOrEmpty()) {
                    Log.i("", "recipe is null or empty")
                    recipes.value = recipePageList
                } else {
                    Log.i("", "recipe list appended")
                    val oldList = recipes.value!!
                    val joined = ArrayList<PublicRecipe>()
                    joined.addAll(oldList)
                    joined.addAll(recipePageList)
                    recipes.value = joined
                }
        }
        viewModelScope.launch {
            recipePage = repository.getPublicRecipes(pageNumber) as MutableLiveData<List<PublicRecipe>>
        }

    }

    fun loadNextPage() {
        recipePage = repository.getPublicRecipes(pageNumber) as MutableLiveData<List<PublicRecipe>>
    }
}