package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import kotlinx.coroutines.launch


class RecipeListViewmodel(repository: PrivateRecipeRepository) : ViewModel() {

    var repo = repository



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


    var recipes : LiveData<List<PrivateRecipe>> = repository.getPrivateRecipes()



    fun deleteRecipe(id: Int?) {
        if(id !=null) {
            //coroutine
            viewModelScope.launch {
                try {
                    repo.deletePrivateRecipe(id)
                } catch (error: Error) {
                    _errorLiveDataString.value = error.message
                }
            }
        }

    }


}