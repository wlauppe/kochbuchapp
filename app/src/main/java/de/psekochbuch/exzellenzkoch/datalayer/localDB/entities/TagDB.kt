package de.psekochbuch.exzellenzkoch.datalayer.localDB.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tag")
data class TagDB(
    @PrimaryKey var tag:String
)