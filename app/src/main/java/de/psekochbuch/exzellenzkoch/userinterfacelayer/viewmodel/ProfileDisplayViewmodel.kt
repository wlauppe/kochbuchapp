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
 * The ProfileDisplayViewmodel hanldes the data for the ProfileDisplayFragment.
 * @param userRepository: the repository through which the user related methods are managed.
 * @param recipeRepository: the repository through which the recipe related methods are managed.
 */
class ProfileDisplayViewmodel(userRepository:UserRepository,
                              recipeRepository: PublicRecipeRepository) : ViewModel() {

    private val recipeRepo = recipeRepository
    var userRepo = userRepository


    //User Information LiveData
    var user  = MutableLiveData(User(""))
        var userID: MutableLiveData<String> = MutableLiveData("")
        var userDesc : MutableLiveData<String> = MutableLiveData("")
        var userImg : MutableLiveData<String> = MutableLiveData("")


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


    //Recipe Information LiveData
    var recipes: MutableLiveData<List<PublicRecipe>> = MutableLiveData()


    fun isOwner(): Boolean {
        return true // TODO implement
    }

    fun setUserByID(id: String) {
        //SetUserID Dummy
        if (id == "") {
            return
        }

        recipes = recipeRepo.getRecipesFromUser(id) as MutableLiveData<List<PublicRecipe>>

        user = userRepo.getUser(id) as MutableLiveData<User>


       /* viewModelScope.launch {
            try {
                 user = userRepo.getUser(id) as MutableLiveData<User>

            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }*/

    }

     fun flagUserById() {
         //Coroutine
        viewModelScope.launch {
            try {
                userRepo.reportUser(user.value?.userId.toString())
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
         }
     }
}
