package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification

/**
 * The Login ViewModel handles the information for the LoginFragment.
 * @param authentification: The interface through which the user authenfication methods are handled.
 */
class LoginViewModel() : ViewModel() {


    var email: MutableLiveData<String> = MutableLiveData("")

    var password: MutableLiveData<String> = MutableLiveData("")

    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData(false)


    /**
     * Login the User at Firebase
     *
     * @param [email] The email of the User
     * @param [password] The password of the User
     *
     */
    fun login(updateUi: (String, AuthenticationResult, String?) -> Unit) {


        val em = email.value
        val pw = password.value

        if(em != null && pw != null && em != "" && pw != "") {
            AuthentificationImpl.login(em, pw) { token, result ->
                if(result == AuthenticationResult.LOGINSUCCESS) {
                    updateUi(AuthentificationImpl.getUserId(), AuthenticationResult.LOGINSUCCESS, "user login")
                } else {
                    updateUi("", AuthenticationResult.LOGINFAILED, "Cannot connect to firebase")
                }
            }
        } else
        {
            updateUi("", AuthenticationResult.LOGINFAILED, "email or password are empty")
        }

    }

    /**
     * Change the "Login" Button in the menu to "Logout" when logged in
     */
    private fun onLoginSuccess() {

    }


}