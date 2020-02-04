package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository


class FeedViewModel: ViewModel() {

    var fakeRepository: PublicRecipeFakeRepository = PublicRecipeFakeRepository()

    var recipes = fakeRepository.getPublicRecipes()
}