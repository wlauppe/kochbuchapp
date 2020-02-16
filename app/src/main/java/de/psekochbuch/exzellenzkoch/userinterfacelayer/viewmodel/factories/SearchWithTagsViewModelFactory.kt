package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.TagRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.SearchWithTagsViewModel

/**
 * Factory for creating a SearchWithTagsViewModel with a constructor that takes a
 * TagRepository.
 */
class SearchWithTagsViewModelFactory(private val repository:TagRepository)
    :ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchWithTagsViewModel(repository) as T
    }
}