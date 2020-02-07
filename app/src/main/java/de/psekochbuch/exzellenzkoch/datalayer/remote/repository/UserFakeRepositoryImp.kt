package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository

class UserFakeRepositoryImp : UserRepository  {
    override fun getUsers(): LiveData<List<User>> {
        val user1 = User("Jürgern", "bild", "Toastbrot")
        val user2 = User("Bürgern", "bild", "Toast")
        val user3 = User("Lürgern", "bild", "Moin")

    val list = listOf<User>(user1, user2, user3)
    val ld : MutableLiveData <List<User>> = MutableLiveData(list)
    return ld

    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: UserRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserFakeRepositoryImp().also { instance = it }
            }
    }

}
