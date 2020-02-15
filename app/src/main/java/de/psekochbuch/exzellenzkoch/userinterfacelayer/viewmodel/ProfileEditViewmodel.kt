package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository

class ProfileEditViewmodel(repo:UserRepository) : ViewModel() {
    var repo = repo
    var user = MutableLiveData<User>()
    var userImg = MutableLiveData<String>()




    /**
     * Removes the user from the database on the server. The Logindata gets resetted and the profil
     * can no longer be accessed
     * @param id: The userId which connects to the user
     */
    fun deleteUser(id: String) {


    }

    /**
     * Gets the User which is connected with the id. Checks if the User exists and if the user is
     * valid it sets the LiveData from the viewmodel with the data.
     * @param id:
     */
    fun setUserByID(id:String) {
        user = repo.getUser(id) as MutableLiveData<User>

    }
    fun changeLoginData() {


    }

    fun save() {

    }
}


