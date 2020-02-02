package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

class ProfileDisplayViewmodel : ViewModel() {

    var repo = PublicRecipeFakeRepository()
    var recipes: LiveData<MutableList<PublicRecipe>> =
        repo.getPublicRecipes() as LiveData<MutableList<PublicRecipe>>


    fun isOwner(): Boolean {
        return true

    }
}