package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.InjectorUtils
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification

class ChangePasswordViewmodel(authentification: Authentification) : ViewModel() {

    var password: MutableLiveData<String>? = null


    fun changePassword() {
        //repo.pwEdit(password)

        //TODO
    }

    fun onClick() {
        //repo.pwEdit(password)

    }
}


