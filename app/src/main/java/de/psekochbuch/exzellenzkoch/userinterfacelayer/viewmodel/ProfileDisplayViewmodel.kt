package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.interfaceimplementation.serviceimplementations.PublicRecipeFakeRepository


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class ProfileDisplayViewmodel : ViewModel() {

    private var repo = PublicRecipeFakeRepository()

    var recipes: LiveData<List<PublicRecipe>> =repo.getPublicRecipes()
    var userList : LiveData<List<User>> = repo.getUsers()

    // get exmple user from list
    var user : User = repo.getUser()

    // set user attributes for the xml
    var username: String? = user.userID
    var userDescription: String? = user.description


    fun isOwner(): Boolean {
        return true // TODO implement
    }
}
