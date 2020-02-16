package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification
import kotlinx.coroutines.launch

/**
 * The ChangePasswordViewModel handles the information for the ChangePasswordFragment.
 *
 */
class ChangePasswordViewModel(authentificationImpl: Authentification) : ViewModel() {


    var authentification = authentificationImpl
    var password: MutableLiveData<String>? = MutableLiveData()
    var user: User? = null


    private val _errorLiveDataString = MutableLiveData<String?>()
    /**
     * Request a snackbar to display a string.
     */
    val errorLiveDataString: LiveData<String?>
        get() = _errorLiveDataString

    /**
     * Changes the password from the current User. The new password ist passed via LiveData and given to firebase
     *
     */
    fun changePassword() {
        if(password?.value.isNullOrEmpty()){
            return
        }
        viewModelScope.launch {
            try {
                AuthentificationImpl.pwEdit(password?.value!!){}
            } catch (error: Error) {
                _errorLiveDataString.value = error.message
            }
        }
    }


    /**
     * Sets the Userinformation for the according userID.
     * @param userID: The ID for the wanted User
     */
    fun setUserById(userID:String){
        
    }
}


