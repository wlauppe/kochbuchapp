package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.TagDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.TagDB
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.TagList

/**
 * his class implements the access to the private tag database
 */
class TagRepositoryImp(application: Application?){
    private val tagDao: TagDao? = DB.getDatabase(application!!)?.tagDao()

    /**
     * this method returns all tags saved in the DB
     * @return the tags wrapped in LiveData
     */
    fun getTags():LiveData<TagList>{
        return Transformations.map(tagDao?.getTags()!!,::transformListTagDBToTagList)
    }

    fun transformListTagDBToTagList(tags:List<TagDB>):TagList{
        return TagList(tags.map{tag -> tag.tag})
    }



}