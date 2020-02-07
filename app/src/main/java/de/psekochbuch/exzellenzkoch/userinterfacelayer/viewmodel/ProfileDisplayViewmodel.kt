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
    //private val userRepo = repository
    var userRepo = UserFakeRepositoryImp()



    //User Information LiveData
    private lateinit var user: User
        var userList : LiveData<List<User>> = userRepo.getUsers()
        var userID : LiveData<String> = MutableLiveData("nutzer ID")
        var userDesc : LiveData<String> = MutableLiveData("beschreibung")
        var userImg : LiveData<String> = MutableLiveData("")

    //Recipe Information LiveData
    var recipes: LiveData<List<PublicRecipe>> =recipeRepo.getPublicRecipes()




    fun isOwner(): Boolean {
        return true // TODO implement
    }

    fun setUserByID(id:String){
        var user = userRepo.getUser(id)

        this.userID = MutableLiveData(user.value!!.userId)
        this.userDesc = MutableLiveData(user.value!!.desc)
        this.userImg = MutableLiveData(user.value!!.img)


    }
}
