package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification
import kotlinx.coroutines.launch


class LoginViewModel(authentification: Authentification) : ViewModel() {


    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    var email: MutableLiveData<String>? = null

    var password: MutableLiveData<String>? = null


    /**
     * Login the User at Firebase
     *
     * @param [email] The email of the User
     * @param [password] The password of the User
     * @param [activity] The Aktivity which the method execute
     *
     */
    fun login() {

    }

    /**
     * Change the "Login" Button in the menu to "Logout" when logged in
     */
    private fun onLoginSuccess() {

    }


}