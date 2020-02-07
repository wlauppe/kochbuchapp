package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.TagRepository

class TagFakeRepositoryImp : TagRepository {
    override fun getTags(): TagList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    companion object {

        // For Singleton instantiation
        @Volatile private var instance: TagRepository? = null
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: TagFakeRepositoryImp().also { instance = it }
        }
    }
}