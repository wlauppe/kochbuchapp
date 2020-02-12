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

class ProfileDisplayViewmodel(userRepository:UserRepository,
                              recipeRepository: PublicRecipeRepository) : ViewModel() {

    private val recipeRepo = recipeRepository
    var userRepo = userRepository


    //User Information LiveData
    var user: User? = null
        var userID: LiveData<String> = MutableLiveData(user?.userId)
        var userDesc : LiveData<String> = MutableLiveData(user?.description)
        var userImg : LiveData<String> = MutableLiveData(user?.imgUrl)


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
    var recipes: LiveData<List<PublicRecipe>> = recipeRepo.getPublicRecipes()


    fun isOwner(): Boolean {
        return true // TODO implement
    }

    fun setUserByID(id: String) {
        if (id == "") {
            return
        }
        viewModelScope.launch {
            try {
                val user = userRepo.getUser(id)
                userDesc = MutableLiveData(user.value!!.description)

            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }

    }

     fun flagUserById() {
         //Coroutine
        viewModelScope.launch {
            try {
                userRepo.reportUser(userID.toString())
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
         }
     }
}
