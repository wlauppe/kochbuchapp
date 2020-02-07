package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ChangePasswordViewmodel

class ChangePasswordViewModelFactory(private val authentification: Authentification) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChangePasswordViewmodel(
            authentification
        ) as T
    }

}