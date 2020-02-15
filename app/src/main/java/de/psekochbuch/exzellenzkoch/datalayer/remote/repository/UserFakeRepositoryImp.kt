package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository

class UserFakeRepositoryImp : UserRepository  {
    var userList : MutableList<User> = mutableListOf()


    init{
        val user1 = User("Jürgen", "", "Toastbrot")
        val user2 = User("Heiner", "https://s.gravatar.com/avatar/f849c680f420d89b5b0b49979d1df5ec?s=80", "Toast")
        val user3 = User("Magnus", "", "Moin")
        val user4 = User("Lea", "", "Toastbrot")
        val user5 = User("Pascal", "", "Toast")
        val user6 = User("Thomas", "", "Moin")

        addToList(user1)
        addToList(user2)
        addToList(user3)
        addToList(user4)
        addToList(user5)
        addToList(user6)

    }
    private fun addToList (user : User){
        var myUser = user
        userList.add(myUser)
    }


    fun getUsers(): LiveData<List<User>> {
        val ld: MutableLiveData<List<User>> = MutableLiveData(userList)
        return ld

    }


    override fun getUser(UserId: String): LiveData<User> {
        var mld = MutableLiveData(getUsers().value!![1])
        return mld
    }

    override suspend fun checkUser(userId: String): User? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteUser(userId: String) {
        for(iterator in userList.toList()){
            if(iterator.userId.equals(userId)){
                userList.remove(iterator)
            }
        }
    }

    override suspend fun addUser(userId: String) : String{
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateUser(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getReportedUsers(): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun reportUser(userId: String) {
        var tempUser = User(userId, "", "testUser")
        this.addToList(tempUser)

    }

    override suspend fun unreportUser(userId: String) {

        for(iterator in userList.toList()){
            if(iterator.userId.equals(userId)){
                userList.remove(iterator)
            }
        }
         }

    override fun setToken(token: String) {
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
