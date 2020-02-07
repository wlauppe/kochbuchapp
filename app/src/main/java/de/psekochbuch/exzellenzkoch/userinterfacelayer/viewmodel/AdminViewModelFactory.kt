package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


/**
 * Factory for creating a RecipeListViewModel with a constructor that takes a
 * PublicRecipeRepository.
 */

class AdminViewModelFactory(
    private val repository: PublicRecipeRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AdminViewModel(repository) as T
    }
}