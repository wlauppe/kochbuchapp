package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.TagRepository

/**
 * The EditTagViewmodel handles the information for the EditTagFragment.
 * @param repository: The Tagrepository through which all the tag interactions are managed.
 */
class EditTagViewmodel(repository:TagRepository) : ViewModel() {

}