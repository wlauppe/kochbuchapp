package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.EspressoIdlingResource
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.launch

/**
 * The RecipeListViewmodel handles the information for the RecipeListFragment.
 * @param privateRepository: The repository through which the private recipes are managed.
 * @param publicRepo: The repository through which the public-recipe-related methods
 * are handled.
 */
class RecipeListViewmodel(privateRepository: PrivateRecipeRepository,
                          publicRepo: PublicRecipeRepository) : ViewModel() {

    var privateRepo = privateRepository
    val publicRepo = publicRepo
    var recipes : LiveData<List<PrivateRecipe>> = MutableLiveData(emptyList())

    /*
* This variable is private because we don't want to expose MutableLiveData
*
* MutableLiveData allows anyone to set a value, and MainViewModel is the only
* class that should be setting values.
*/
    private val _errorLiveDataString = MutableLiveData<String?>()
    /**
     * Request a snackbar to display a string.
     */
    val errorLiveDataString: LiveData<String?>
        get() = _errorLiveDataString

    /**
     * Returns the private recipes from the particular user.
     */
    fun getPrivateRecipes() {
        EspressoIdlingResource.increment()
        recipes = privateRepo.getPrivateRecipes()
        EspressoIdlingResource.decrement()
    }

    /**
     * Deletes the recipe from the local database and the server.
     * @param id: the id from the specific user which should be deleted
     */
    fun deleteRecipe(id: Int?) {
        if(id !=null) {
            //coroutine
            viewModelScope.launch {
                try {
                    privateRepo.deletePrivateRecipe(id)
                } catch (error: Error) {
                    _errorLiveDataString.value = error.message
                }
            }
            this.recipes.value?.forEach {if(it.recipeId == id){
                if(it.publishedRecipeId != 0){
                    viewModelScope.launch {
                        try {
                            publicRepo.deleteRecipe(it.publishedRecipeId)
                        } catch (error: Error) {
                            _errorLiveDataString.value = error.message
                        }
                    }
                }
            }
            }
        }
    }
}