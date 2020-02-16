package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileDisplayViewmodel

/**
 * Factory for creating a ProfileDisplayViewModel with a constructor that takes a
 * userRepository, recipeRepository.
 */
class ProfileDisplayViewModelFactory(private val userRepository: UserRepository,
                                     private val recipeRepository: PublicRecipeRepository
)
    :ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileDisplayViewmodel(userRepository, recipeRepository) as T
    }
}