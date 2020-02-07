package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


class FeedViewModel(repository: PublicRecipeRepository): ViewModel() {
    var recipes = repository.getPublicRecipes()
}