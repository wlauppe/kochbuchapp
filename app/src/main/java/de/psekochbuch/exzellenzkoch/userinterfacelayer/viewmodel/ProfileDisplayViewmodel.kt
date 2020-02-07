package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.databinding.ProfileDisplayFragmentBinding
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.PublicRecipeFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp


import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository

class ProfileDisplayViewmodel(repository:UserRepository) : ViewModel() {

    private val recipeRepo = PublicRecipeFakeRepositoryImp()
    private val userRepo = UserFakeRepositoryImp()
    var user = userRepo.getUsers()

    var recipes: LiveData<List<PublicRecipe>> =recipeRepo.getPublicRecipes()
    var userList : LiveData<List<User>> = userRepo.getUsers()
    var userID : LiveData<String> = MutableLiveData("nutzer ID")
    var userDesc : LiveData<String> = MutableLiveData("beschreibung")

    var userImg = "https://yt3.ggpht.com/a-/ACSszfEhlI0KLIrMGe6pYgUSgYfAV36seXsy4DgyhQ=s900-mo-c-c0xffffffff-rj-k-no"
    // get exmple user from list
  //  var user : User? = userList.value?.get(0)

    // set user attributes for the xml



    fun isOwner(): Boolean {
        return true // TODO implement
    }
}
