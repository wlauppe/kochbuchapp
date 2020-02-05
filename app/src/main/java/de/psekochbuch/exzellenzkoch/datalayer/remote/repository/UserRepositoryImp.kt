package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository

class UserRepositoryImp : UserRepository  {
    override fun getUsers(): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}