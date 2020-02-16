package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.TagRepository
import de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel.EditTagViewmodel
/**
 * Factory for creating a EditTagViewModel with a constructor that takes a
 * TagRepository.
 */
class EditTagViewModelFactory(private val repository: TagRepository)
    :ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditTagViewmodel(repository) as T
    }
}