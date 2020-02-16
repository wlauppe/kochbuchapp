package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.view.RecipeDisplayFragment
import kotlinx.coroutines.launch
import java.util.*

class RecipeDisplayViewmodel(repository:PublicRecipeRepository) : ViewModel() {
    var Tag = "RecipeDisplayViewmodel"

    val repository=repository

    var recipe = MutableLiveData(PublicRecipe())

    //Get PublicRecipeFrom Repo and set it as the current Recipe
    fun setRecipeByID(id:Int?){
        Log.i(Tag, "SetRecipe BY ID $id")
        if(id == null){
            return
        }
        recipe = repository.getPublicRecipe(id) as MutableLiveData<PublicRecipe>
    }

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
     * Sends the given ID to the repository which adds it to the reported recipe list
     * @param id: The recipe ID
     */
    fun reportRecipe(id: Int){
        viewModelScope.launch {
            try {
                repository.reportRecipe(id)
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }
    }

//Wunschkriterium
    fun addToFavourites() {
        //repo.addToFacourites(id)
    }


}