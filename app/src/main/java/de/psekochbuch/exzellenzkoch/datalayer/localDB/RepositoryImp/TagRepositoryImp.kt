package de.psekochbuch.exzellenzkoch.datalayer.localDB.RepositoryImp

import android.app.Application
import androidx.lifecycle.LiveData
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Daos.TagDao
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList
import androidx.lifecycle.Transformations
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.TagDB

class TagRepositoryImp(application: Application?){
    private val tagDao: TagDao? = DB.getDatabase(application!!)?.tagDao()

    fun getTags():LiveData<TagList>{
        return Transformations.map(tagDao?.getTags()!!,::transformListTagDBToTagList)
    }

    fun transformListTagDBToTagList(tags:List<TagDB>):TagList{
        return TagList(tags.map{tag -> tag.tag})
    }
}