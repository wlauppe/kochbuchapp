package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.userinterfacelayer.AuthenticationResult

class RegistrationViewModel : ViewModel() {


    var email: MutableLiveData<String> = MutableLiveData()
    var userId: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData(false)

    fun registerOnClick() {
        //email.postValue("bal")
        progressBarVisibility.postValue(true)
        val em = email.value
        val pw = password.value
        if (em != null && pw != null) {
            AuthentificationImpl()
                .register(em, pw) { it, result ->
                    if (it != null && result == AuthenticationResult.REGISTRATIONSUCCESS) {
                        Log.d(TAG, "Registration erfolgreich")
                        progressBarVisibility.postValue(false)
                    } else {
                        progressBarVisibility.postValue(false)
                    }
                }
        }
        progressBarVisibility.postValue(false)
    }
}