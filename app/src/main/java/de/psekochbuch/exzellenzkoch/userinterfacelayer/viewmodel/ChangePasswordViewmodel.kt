package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

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


