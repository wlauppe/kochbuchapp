package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository

class UserFakeRepositoryImp : UserRepository  {


    override fun getUsers(): LiveData<List<User>> {
        val user1 = User("Jürgen", "bild", "Toastbrot")
        val user2 = User("Heiner", "https://s.gravatar.com/avatar/f849c680f420d89b5b0b49979d1df5ec?s=80", "Toast")
        val user3 = User("Olaf", "bild", "Moin")

    val list = listOf<User>(user1, user2, user3)
    val ld : MutableLiveData <List<User>> = MutableLiveData(list)
    return ld

    }


    override fun getUsers(userIdPraefix: String): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(UserId: String): LiveData<User> {
        var mld = MutableLiveData(getUsers().value!![1])
        return mld
    }

    override suspend fun deleteUser(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addUser(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getReportedUsers(): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun reportUser(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun unreportUser(userId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
