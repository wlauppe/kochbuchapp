package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository

class PublicRecipeSearchViewmodel(repository:PublicRecipeRepository) : ViewModel() {

    var recipeTitle: LiveData<String>? = null
}