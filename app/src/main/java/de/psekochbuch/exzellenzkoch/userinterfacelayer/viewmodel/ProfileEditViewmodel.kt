package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * The ProfileEditViewmodel handles the information for the ProfileEditFragment.
 * @param repo: the user repository through which the user related methods are executed.
 */
class ProfileEditViewmodel(var repo: UserRepository, val publicRepo: PublicRecipeRepository, val privateRepo: PrivateRecipeRepository) : ViewModel() {

    var user : MutableLiveData<User> = MutableLiveData(User(""))

        //LiveData
        var userID : MutableLiveData<String> = MutableLiveData("")
        var userDesc : MutableLiveData<String> = MutableLiveData("")
        var userImgURL  : MutableLiveData<String> = MutableLiveData("")



    /**
     * Removes the user from the database on the server. The Logindata gets resetted and the profil
     * can no longer be accessed
     * @param id: The userId which connects to the user
     */
    fun deleteUser(id: String) {
        privateRepo.getAllPublishedIds().observeForever{
            runBlocking {
                for (id:Int in it)
                    publicRepo.deleteRecipe(id)
            }
        }

        AuthentificationImpl.userDelete()
    }

    /**
     * Gets the User which is connected with the id. Checks if the User exists and if the user is
     * valid it sets the LiveData from the viewmodel with the data.
     * @param id:
     */
    fun setUserByID(id:String){
        if(id != "") {
            user = repo.getUser(id) as MutableLiveData<User>
        }

    }
    fun changeLoginData() {

    }

    /**
     * Stores the users changes
     */
    fun save() {
        var newUser = User(user.value!!.userId,user.value!!.imgUrl,user.value!!.description)
        viewModelScope.launch {
            try {
                repo.updateUser(userID.value!!,newUser)
            } catch (error: Error) {
            }
        }

    }
}


