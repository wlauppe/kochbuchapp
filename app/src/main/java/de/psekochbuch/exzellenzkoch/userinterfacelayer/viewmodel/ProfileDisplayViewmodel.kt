package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import kotlinx.coroutines.launch

class ProfileDisplayViewmodel(repository:UserRepository) : ViewModel() {

    private val recipeRepo = PublicRecipeFakeRepositoryImp()
    //private val userRepo = repository
    var userRepo = UserFakeRepositoryImp()


    //User Information LiveData
    private lateinit var user: User
    var userList: LiveData<List<User>> = userRepo.getUsers()
    var userID: LiveData<String> = MutableLiveData("nutzer ID")
    var userDesc: LiveData<String> = MutableLiveData("beschreibung")
    var userImg: LiveData<String> = MutableLiveData("")


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

        this.userID = MutableLiveData(user.value!!.userId)
        this.userDesc = MutableLiveData(user.value!!.description)
        this.userImg = MutableLiveData(user.value!!.imgUrl)


    }

    fun flagUserById() {
        if (userID.value.isNullOrBlank()) {
            return


        }

        //Coroutine
        //userRepo.reportUser(userID.value!!)

        viewModelScope.launch {
            try {
                userRepo.reportUser(userID.value!!)
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }


        }
    }
}