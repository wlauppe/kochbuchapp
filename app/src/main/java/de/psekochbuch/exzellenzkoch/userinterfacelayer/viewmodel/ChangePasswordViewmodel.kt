package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.R

import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification

class ChangePasswordViewmodel(@NonNull application: Application) : AndroidViewModel(application) {

    var password: MutableLiveData<String>? = null



    fun changePassword() {
        //repo.pwEdit(password)

        //TODO
    }

    fun onClick() {
        //repo.pwEdit(password)


    }
}


