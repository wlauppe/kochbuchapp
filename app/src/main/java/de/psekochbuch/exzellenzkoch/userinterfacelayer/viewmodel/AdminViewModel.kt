package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import kotlinx.coroutines.launch


/**
 * The AdminViewModel handles the Information for the AdminFragment.
 * @param publicRecipeRepo: The public repository through which the public recipes are handled
 * @param userRepo: The user repository through which the users are handled
 */
class AdminViewModel(publicRecipeRepo : PublicRecipeRepository, userRepo:UserRepository) : ViewModel() {
    var rRepo = publicRecipeRepo
    var uRepo = userRepo


    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */
    var recipes : LiveData<List<PublicRecipe>> = rRepo.getReportedPublicRecipes()
    var users : LiveData<List<User>> = uRepo.getReportedUsers()

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
           *Removes the recipe which matches the given id from the Database on the server. The reciper can not longer
           * be accessed.
           * @param id the id of the recipe
           */
    fun deleteRecipe(recipeID: Int) {
        viewModelScope.launch {
            try {

                rRepo.deleteRecipe(recipeID)
                recipes = rRepo.getReportedPublicRecipes()
                //TODO letztes Rezept darf nicht gelöscht werden
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }

    }

    /**
       *Removes the user which matches the given id from the Database on the server. The user can not longer
       * be accessed.
       * @param id the Userid from the reported user
       * */
    fun deleteUser(userID: String) {
        viewModelScope.launch {
            try {
                uRepo.deleteUser(userID)
                users   = uRepo.getReportedUsers()
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }
    }

    /**
   *Removes the user which matches the given userID from the reported users  list.
   * @param id the id from the reported user
   * */
    fun unreportUser(id : String){
        viewModelScope.launch {
            try {
                uRepo.unreportUser(id)
                users   = uRepo.getReportedUsers()
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }
    }

    /**
    *Removes the recipe which matches the given id from the reported list.
    * @param id the id from the reported recipe
    * */
    fun unreportRecipe(id: Int) {
        viewModelScope.launch {
            try {
                rRepo.unreportRecipe(id)
                recipes = rRepo.getReportedPublicRecipes()
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }
    }
}
