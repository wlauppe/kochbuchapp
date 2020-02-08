package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class ProfileEditViewmodel : ViewModel() {
    var user : LiveData<User> = UserFakeRepositoryImp().getUser("")

        //LiveData
        var userID : LiveData<String> = MutableLiveData("")
        var userDesc : LiveData<String> = MutableLiveData("")
        var userImgURL : LiveData<String> = MutableLiveData("")


    /**
     * Removes the user from the database on the server. The Logindata gets resetted and the profil
     * can no longer be accessed
     * @param id: The userId which connects to the user
     */
    fun deleteUser(id: String) {


    }

    /**
     * Gets the User which is connected with the id. Checks if the User exists and if the user is
     * valid it sets the LiveData from the viewmodel with the data.
     * @param id:
     */
    fun setUserByID(id:String){
        var user = UserFakeRepositoryImp().getUser(id)
        if(user.value!!.userId == ""){

        }
        this.user = user
        this.userID = MutableLiveData(user.value!!.userId)
        this.userDesc = MutableLiveData(user.value!!.description)
        this.userImgURL = MutableLiveData(user.value!!.imgUrl)
    }

    fun changeLoginData(){


    }

}


