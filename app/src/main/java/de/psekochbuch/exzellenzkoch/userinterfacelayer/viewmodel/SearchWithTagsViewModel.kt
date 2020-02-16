package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.TagRepository

/**
 * The SearchWithTagsViewmodel handles the information for the SearchWithTagsFragment.
 * @param repository : the tag repository through which the tag related methods are called.
 */
class SearchWithTagsViewModel(repository:TagRepository) : ViewModel() {

}