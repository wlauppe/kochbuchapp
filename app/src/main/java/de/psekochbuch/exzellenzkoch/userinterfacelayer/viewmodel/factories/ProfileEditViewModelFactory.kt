package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.ProfileEditViewmodel

/**
 * Factory for creating a ProfileEditViewModel with a constructor that takes a
 * UserRepository.
 */
class ProfileEditViewModelFactory(private val repo:UserRepository, private val publicRepo: PublicRecipeRepository, private val privateRepo: PrivateRecipeRepository):
        ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileEditViewmodel(
            repo, publicRepo, privateRepo
        ) as T
    }
}