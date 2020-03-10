package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.launch

/**
 *
 * The FeedViewmodel handles the information for the Feedfragment.
 * @param repository: The repository through which the public recipes are handled.
 */
class FeedViewModel(val repository: PublicRecipeRepository): ViewModel() {
    fun loadPage(page: Int) {
        viewModelScope.launch {

            if(recipes.value.isNullOrEmpty())
            {
                recipes.value = repository.getPublicRecipes(page).value
            } else
            {
                val newRecipes = repository.getPublicRecipes(page)
                val oldList = recipes.value!!
                val joined = ArrayList<PublicRecipe>()
                joined.addAll(oldList)
                joined.addAll(newRecipes.value!!)
                recipes.value = newRecipes.value
            }
            isLoading = false

        }


    }

    var isLastPage = false
    var isLoading = false
    var pageNumber:Int = 1
    var recipes = MutableLiveData<List<PublicRecipe>>()


}
