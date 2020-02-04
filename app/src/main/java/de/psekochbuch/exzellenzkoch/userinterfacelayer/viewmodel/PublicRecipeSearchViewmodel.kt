package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PublicRecipeSearchViewmodel : ViewModel() {

    var recipeTitle: LiveData<String>? = null
}