package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class ProfileDisplayViewmodel : ViewModel() {

    var repo = PublicRecipeFakeRepository()
    var recipes: LiveData<List<PublicRecipe>> =repo.getPublicRecipes()

    var users : LiveData<List<User>> = repo.getUsers()




    fun isOwner(): Boolean {
        return true

    }
}