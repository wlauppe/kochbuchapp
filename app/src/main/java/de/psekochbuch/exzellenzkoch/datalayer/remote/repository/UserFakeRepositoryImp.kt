package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
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

    override fun getUsers(userIdPraefix: String): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
}