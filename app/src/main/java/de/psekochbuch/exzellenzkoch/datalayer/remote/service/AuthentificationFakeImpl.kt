package de.psekochbuch.exzellenzkoch.datalayer.remote.service

import android.util.JsonToken
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.services.Authentification

class AuthentificationFakeImpl:Authentification {
    override suspend fun register(email: String, password: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun login(email: String, password: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun logout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isLoggedIn(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getToken(): JsonToken {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun editPassword() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteUser() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isAdmin(userId: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun editUser(userId: String, description: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: Authentification? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: AuthentificationFakeImpl().also { instance = it }
        }
    }

}