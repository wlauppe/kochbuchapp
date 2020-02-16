package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeDisplayViewmodel

/**
 * Factory for creating a RecipeDisplayViewModel with a constructor that takes a
 * PublicRecipeRepository.
 */
class RecipeDisplayViewModelFactory(private val repo: PublicRecipeRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeDisplayViewmodel(
            repo
        ) as T
    }
}