package com.example.simplekochbuchapp.domainlayer.interfaces.repository

import com.example.simplekochbuchapp.domainlayer.domainentities.User

interface UserRepository {

    /**
     * Return the user (needed to load users from recipes eg)
     */
    suspend fun getUser(id:String): User

    /**
     * Methods we still need:
     *
     * reportUser()
     * deleteUser(id:String)
     *
     */
}