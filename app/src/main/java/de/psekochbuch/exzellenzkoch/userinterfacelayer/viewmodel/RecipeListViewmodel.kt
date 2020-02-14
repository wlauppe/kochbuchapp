package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.launch


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


    fun getPrivateRecipes() {
        recipes = privateRepo.getPrivateRecipes()
    }


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
            this.recipes.value?.forEach {
                if(it.recipeId == id){
                if(it.publishedRecipeId != null){
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