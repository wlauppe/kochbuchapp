package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository


class FeedViewModel: ViewModel() {

    var repository: PublicRecipeRepository = PublicRecipeFakeRepositoryImp()

    var recipes = repository.getPublicRecipes()
}