package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileDisplayViewmodel(repository:UserRepository) : ViewModel() {

    private val recipeRepo = PublicRecipeFakeRepositoryImp()
    //private val userRepo = repository
    var userRepo = repository


    //User Information LiveData
    private lateinit var user: User
        var userList : List<User> = userRepo.getUsers().value!!
        var userID : String = ""
        var userDesc : String = ""
        var userImg : String = ""


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
        var user = userRepo.getUser(id)
        this.userID = user.value!!.userId
        this.userDesc =user.value!!.description
        this.userImg = user.value!!.imgUrl


    }

     fun flagUserById() {
         if (userID.isNullOrBlank()) {
             return


         }
         //Coroutine
         //userRepo.reportUser(userID.value!!)

        viewModelScope.launch {
            try {
                userRepo.reportUser(userID)
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }


         }
     }
}
