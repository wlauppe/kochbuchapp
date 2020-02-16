package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification


import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import kotlinx.coroutines.launch

class RegistrationViewModel(authentification: Authentification, repo: UserRepository) :
    ViewModel() {


    var email: MutableLiveData<String> = MutableLiveData()
    var userId: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData(false)
    var focusable: MutableLiveData<Boolean> = MutableLiveData(true)

    private val userRepository = repo

    fun registerOnClick(updateUi: (String?, AuthenticationResult, String?) -> Unit) {

        val em = email.value
        val pw = password.value
        if (em != null && pw != null) {

            val id = userId.value
            if (id != "" && id != null) {
                viewModelScope.launch {
                    try {
                        val user: User? = userRepository.checkIsUserIdRegistered(id)
                        if (user?.userId == "") {

                            AuthentificationImpl
                                .register(em, pw, id) { it, result ->
                                    if (result == AuthenticationResult.USERIDNOTSET) {
                                        updateUi("", result, "Account created. Userid not saved. connection lost")

                                    } else if (it == null && result == AuthenticationResult.REGISTRATIONSUCCESS) {
                                        updateUi("", AuthenticationResult.USERNOTSERVERCREATED, "User must be verified")

                                    } else if (it != null && result == AuthenticationResult.REGISTRATIONSUCCESS) {
                                        Log.d(TAG, "Registration erfolgreich")
                                        userRepository.setToken(it)
                                        viewModelScope.launch {
                                            try {
                                                val token = userRepository.addUser(id)
                                                AuthentificationImpl.authWithCustomToken(token) {

                                                    updateUi(
                                                        id,
                                                        AuthenticationResult.REGISTRATIONSUCCESS,
                                                        "User created"
                                                    )

                                                }
                                            } catch (e: Exception) {
                                                updateUi(
                                                    "",
                                                    AuthenticationResult.USERNOTSERVERCREATED,
                                                    "User could not verified"
                                                )
                                            }

                                            progressBarVisibility.postValue(false)
                                        }
                                    } else if (result == AuthenticationResult.REGISTRATIONFAILED) {
                                        updateUi(
                                            "",
                                            result,
                                            "registration failed. connection to server is bad"
                                        )
                                    }
                                }
                        } else {
                            updateUi(
                                "",
                                AuthenticationResult.USERALREADYEXIST,
                                "username already exist"
                            )
                        }

                    } catch (ex: Exception) {

                        updateUi(
                            "",
                            AuthenticationResult.CANNOTCONTECTTOSERVER,
                            "Cannot connect to server"
                        )
                        ex.printStackTrace()
                    }
                }

            } else {

                AuthentificationImpl
                    .register(em, pw, "") { it, result ->
                        if (it == null && result == AuthenticationResult.REGISTRATIONSUCCESS) {
                            updateUi("", AuthenticationResult.USERNOTSERVERCREATED, "User must be verified")

                        } else
                            if (it != null && result == AuthenticationResult.REGISTRATIONSUCCESS) {
                                Log.d(TAG, "Registration erfolgreich")
                                userRepository.setToken(it)
                                viewModelScope.launch {
                                    try {
                                        val token = userRepository.addUser("")
                                        AuthentificationImpl.authWithCustomToken(token) {
                                            updateUi(
                                                AuthentificationImpl.getUserId(),
                                                AuthenticationResult.REGISTRATIONSUCCESS,
                                                "User created"
                                            )

                                        }
                                    } catch (e: Exception) {
                                        updateUi("", AuthenticationResult.USERNOTSERVERCREATED, "Userid could not set")
                                    }
                                }
                            } else {
                                updateUi("", AuthenticationResult.REGISTRATIONFAILED, "could not connect to server")
                            }
                    }
            }

            //email.postValue("bal")
        } else {
            updateUi("", AuthenticationResult.REGISTRATIONFAILED, "email and password empty")
        }
    }

    fun registrationSuccess(): Boolean {
        return true
    }
}