package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.*
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import kotlinx.coroutines.launch
import java.util.*

class RecipeDisplayViewmodel(repository:PublicRecipeRepository) : ViewModel() {

    var repo = repository


    //Das Fragment wird nur aufgerufen wenn ein Rezept ausgewählt wird. Daher nicht lateinit
    var recipe : LiveData<PublicRecipe> = MutableLiveData(PublicRecipe())

    private val _errorLiveDataString = MutableLiveData<String?>()

    var recipeTitle : LiveData<String> = liveData {
        emit(recipe.value!!.title)
    }
    /**
     * Request a snackbar to display a string.
     */
    val errorLiveDataString: LiveData<String?>
        get() = _errorLiveDataString
        var creationDate = "Erstellungsdatum: 2020"


    //Get PublicRecipeFrom Repo and set it as the current Recipe
    fun setRecipeByID(id:Int?){
    if(id == null){
        return
    }
        viewModelScope.launch {
            try {
               recipe = repo.getPublicRecipe(id)
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