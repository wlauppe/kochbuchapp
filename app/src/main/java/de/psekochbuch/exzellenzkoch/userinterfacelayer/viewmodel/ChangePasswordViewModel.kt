package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification

class ChangePasswordViewModel(authentification: Authentification) : ViewModel() {


    var authentification = authentification
    var password: MutableLiveData<String>? = null
    var user: User? = null

    /**
     * Changes the password from the current User. The new password ist passed via LiveData and given to firebase
     *
     */
    fun changePassword() {
        //repo.pwEdit(password)

    }


    /**
     * Sets the Userinformation for the according userID.
     * @param userID: The ID for the wanted User
     */
    fun setUserById(userID:String){
        
    }
}


