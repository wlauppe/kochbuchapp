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


class AdminViewModel(publicRecipeRepo : PublicRecipeRepository, userRepo:UserRepository) : ViewModel() {
    var rRepo = publicRecipeRepo
    var uRepo = userRepo


    /*Das ViewModel sollte eine Liste der Rezepte verwalten Der Adapter zeigt nur die Namen und besitzt
    * eine Liste an ID`s, um ein ausgewähltes Rezept in dem RecipeDisplayFragment laden zu können */


    var recipes : LiveData<List<PublicRecipe>> = rRepo.getPublicRecipes()

    var users : LiveData<List<User>> = userRepo.getUsers()



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


    /* fun refreshTitle() {


        viewModelScope.launch {
            try {
               // _spinner.value = true
                repository.reportUser()
            } catch (error: TitleRefreshError) {
                _snackBar.value = error.message
            } finally {
               // _spinner.value = false
            }
        }
        */


    /**
           *Removes the recipe which matches the given id from the Database on the server. The reciper can not longer
           * be accessed.
           * @param id the id of the recipe
           */
    fun deleteRecipe(recipeID: Int) {
      //  rRepo.deleteRecipe(recipeID)

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
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }


        }
        //uRepo.deleteUser(userID)

    }

    /**
   *Removes the user which matches the given userID from the reported users  list.
   * @param id the id from the reported user
   * */
    fun unreportUser(id : String){
        viewModelScope.launch {
            try {
                uRepo.unreportUser(id)
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
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }


        }
    }


}
