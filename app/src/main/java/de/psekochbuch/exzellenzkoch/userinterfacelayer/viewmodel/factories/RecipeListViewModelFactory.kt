package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.RecipeListViewmodel


/**
 * Factory for creating a RecipeListViewModel with a constructor that takes a
 * PublicRecipeRepository.
 */

class RecipeListViewModelFactory(private val privateRepository: PrivateRecipeRepository,
        private val publicRepo:PublicRecipeRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RecipeListViewmodel(privateRepository, publicRepo) as T
    }
}