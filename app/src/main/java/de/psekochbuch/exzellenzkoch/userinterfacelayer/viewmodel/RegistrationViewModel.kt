package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.ApiServiceBuilder
import de.psekochbuch.exzellenzkoch.datalayer.remote.api.PublicRecipeApi
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification


import de.psekochbuch.exzellenzkoch.userinterfacelayer.AuthenticationResult
import kotlinx.coroutines.launch

class RegistrationViewModel(authentification:Authentification, repo:UserRepository) : ViewModel() {


    var email: MutableLiveData<String> = MutableLiveData()
    var userId: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData(false)

    fun registerOnClick() {

        val userRepository = UserRepositoryImp()

        progressBarVisibility.postValue(true)
        val id = userId.value
        if(id != "" && id != null){
            viewModelScope.launch {
                try {
                    val user : User? = userRepository.checkUser(id)
                    if(user?.userId == "") {
                        val em = email.value
                        val pw = password.value
                        if (em != null && pw != null) {
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
                                            }catch (e: Exception)
                                            {

                                            }

                                            progressBarVisibility.postValue(false)
                                        }
                                    } else {
                                        progressBarVisibility.postValue(false)
                                    }
                                }
                        }
                        progressBarVisibility.postValue(false)
                    }

                }catch (ex: Exception)
                {
                    ex.printStackTrace()
                }
                finally {
                    progressBarVisibility.postValue(false)
                }
                //Repo anfrage, ob ID schon existiert
                //-> 5 Sekunden Wartezeit für Serverantwort
                //boolean abfrage der SErverantwort
                //-> falls ja : Toast ausgabe und return
                //-> falls nein: unterer code
            }

        } else {
            val em = email.value
            val pw = password.value
            if (em != null && pw != null) {
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
                                }catch (e: Exception)
                                {

                                }

                                progressBarVisibility.postValue(false)
                            }
                        } else {
                            progressBarVisibility.postValue(false)
                        }
                    }
            }
            progressBarVisibility.postValue(false)
        }

        //email.postValue("bal")

    }

    fun registrationSuccess():Boolean{
        return true
    }
}