package com.example.kochbuchappmagnus.datalayer.repositoryimpl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import de.psekochbuch.exzellenzkoch.domainlayer.entity.IngredientAmount

@Dao
interface PrivateTagsDAO {

    @Insert
    fun insert(privateTag: String)

    @Delete
    fun delete(privateTag: String)

    @Update
    fun update(privateTag: String)


}