package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.AdminViewModel


/**
 * Factory for creating a RecipeListViewModel with a constructor that takes a
 * PublicRecipeRepository.
 */

class AdminViewModelFactory(
    private val publicRecipeRepository: PublicRecipeRepository,
    private val userRepository: UserRepository

) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AdminViewModel(
            publicRecipeRepository,
            userRepository
        ) as T
    }
}