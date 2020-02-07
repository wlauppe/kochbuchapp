package de.psekochbuch.exzellenzkoch.userinterfacelayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserFakeRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User

class ProfileEditViewmodel : ViewModel() {
    var user : LiveData<User> = UserFakeRepositoryImp().getUserByID("")

        //LiveData
        var userID : LiveData<String> = MutableLiveData("")
        var userDesc : LiveData<String> = MutableLiveData("")
        var userImgURL : LiveData<String> = MutableLiveData("")


    fun deleteUser(id: String) {


    }

    fun setUserByID(id:String){
        var user = UserFakeRepositoryImp().getUserByID(id)

        this.user = user
        this.userID = MutableLiveData(user.value!!.userId)
        this.userDesc = MutableLiveData(user.value!!.desc)
        this.userImgURL = MutableLiveData(user.value!!.img)

    }
}


