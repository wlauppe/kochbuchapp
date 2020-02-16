package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ChangePasswordViewModel

/**
 * Factory for creating a ChangePasswordViewModel with a constructor that takes a
 * authentification.
 */

class ChangePasswordViewModelFactory(private val authentification: Authentification) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChangePasswordViewModel(
            authentification
        ) as T
    }

}