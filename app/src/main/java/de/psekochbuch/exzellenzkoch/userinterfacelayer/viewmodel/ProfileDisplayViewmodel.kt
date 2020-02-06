package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class ProfileDisplayViewmodel : ViewModel() {

    private val recipeRepo = PublicRecipeFakeRepositoryImp()
    private val userRepo = UserFakeRepositoryImp()

    var recipes: LiveData<List<PublicRecipe>> =recipeRepo.getPublicRecipes()
    var userList : LiveData<List<User>> = userRepo.getUsers()

    // get exmple user from list
    var user : User? = userList.value?.get(0)

    // set user attributes for the xml
    var username: String? = user?.userId
    var userDescription: String? = user?.desc


    fun isOwner(): Boolean {
        return true // TODO implement
    }
}
