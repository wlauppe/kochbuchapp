package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification


import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import kotlinx.coroutines.launch

class RegistrationViewModel(authentification:Authentification, repo:UserRepository) : ViewModel() {


    var email: MutableLiveData<String> = MutableLiveData()
    var userId: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData(true)
    var focusable: MutableLiveData<Boolean> = MutableLiveData(false)

    private val userRepository = repo

    fun registerOnClick(updateUi: (String?, AuthenticationResult, String?) -> Unit) {

        val em = email.value
        val pw = password.value
        if (em != null && pw != null) {

            val id = userId.value
            if (id != "" && id != null) {
                viewModelScope.launch {
                    try {
                        val user: User? = userRepository.checkUser(id)
                        if (user?.userId == "") {

                            AuthentificationImpl
                                .register(em, pw, id) { it, result ->
                                    if (it != null && result == AuthenticationResult.REGISTRATIONSUCCESS) {
                                        Log.d(TAG, "Registration erfolgreich")
                                        userRepository.setToken(it)
                                        viewModelScope.launch {
                                            try {
                                                val token = userRepository.addUser(id)
                                                AuthentificationImpl.authWithCustomToken(token) {
                                                    AuthentificationImpl.getToken {


                                                    }
                                                }
                                            } catch (e: Exception) {

                                            }

                                            progressBarVisibility.postValue(false)
                                        }
                                    } else {
                                        updateUi("", result, "registration failed. connection to server is bad")
                                        //progressBarVisibility.postValue(false)
                                    }
                                }

                            progressBarVisibility.postValue(false)
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

                    } finally {
                        progressBarVisibility.postValue(false)
                    }
                    //Repo anfrage, ob ID schon existiert
                    //-> 5 Sekunden Wartezeit für Serverantwort
                    //boolean abfrage der SErverantwort
                    //-> falls ja : Toast ausgabe und return
                    //-> falls nein: unterer code
                }

            } else {

                AuthentificationImpl
                    .register(em, pw, "") { it, result ->
                        if (it != null && result == AuthenticationResult.REGISTRATIONSUCCESS) {
                            Log.d(TAG, "Registration erfolgreich")
                            userRepository.setToken(it)
                            viewModelScope.launch {
                                try {
                                    val token = userRepository.addUser("")
                                    AuthentificationImpl.authWithCustomToken(token) {
                                        AuthentificationImpl.getToken {


                                        }
                                    }
                                } catch (e: Exception) {

                                }

                                progressBarVisibility.postValue(false)
                            }
                        } else {
                            progressBarVisibility.postValue(false)
                        }
                    }
                progressBarVisibility.postValue(false)
            }

            //email.postValue("bal")
        } else {
            updateUi("", AuthenticationResult.REGISTRATIONFAILED, "email and password empty")
        }
    }

    fun registrationSuccess():Boolean{
        return true
    }
}