package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.CreateRecipeViewmodel

class CreateRecipeViewModelFactory (private val privateRepository: PrivateRecipeRepository,
                                    private val publicRepository:PublicRecipeRepository,
                                    private val userRepository: UserRepository)
    :ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateRecipeViewmodel(privateRepository, publicRepository, userRepository) as T
    }
}