package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.launch


class RecipeListViewmodel(repository: PublicRecipeRepository) : ViewModel() {

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


    var recipes : LiveData<List<PublicRecipe>> = repository.getPublicRecipes()



    fun deleteRecipe(id: Int?) {

        /*
        //coroutine
        viewModelScope.launch {
            try {
                repo.deleteRecipe(id!!)
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }
         */
    }


}