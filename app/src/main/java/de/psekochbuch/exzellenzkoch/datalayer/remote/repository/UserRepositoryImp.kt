package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository

class UserRepositoryImp : UserRepository  {
    override suspend fun getUser(id: String): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}