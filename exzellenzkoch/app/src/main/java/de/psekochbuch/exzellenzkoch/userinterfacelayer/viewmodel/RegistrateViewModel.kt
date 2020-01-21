package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.Authentification

class RegistrateViewModel : ViewModel() {



    var email : MutableLiveData<String> = MutableLiveData()
    var userId : MutableLiveData<String> = MutableLiveData()
    var password : MutableLiveData<String> = MutableLiveData()
    var progressBarVisibility : MutableLiveData<Boolean> = MutableLiveData(false)

    fun registrateOnClick()
    {
        email.postValue("bal")
        progressBarVisibility.postValue(true)
        val em = email.value
        val pw = password.value
        if(em != null && pw != null) {
            Authentification().registrate(em, pw) { it, result ->
                if(it != null) {
                    Log.d(TAG,"Registration erfolgreich")
                    progressBarVisibility.postValue(false)
                }
                else
                {
                    progressBarVisibility.postValue(false)
                }
            }
        }
    }
}