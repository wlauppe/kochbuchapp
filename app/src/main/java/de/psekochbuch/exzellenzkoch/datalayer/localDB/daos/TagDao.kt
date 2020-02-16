package de.psekochbuch.exzellenzkoch.datalayer.localDB.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.TagDB

@Dao
interface TagDao {
    @Insert(onConflict  = OnConflictStrategy.REPLACE)
    fun insert(tag: TagDB);

    @Query("SELECT * from tag")
    fun getTags():LiveData<List<TagDB>>;

    @Query("DELETE from tag where tag = :tag")
    fun deleteTag(tag:String);
}