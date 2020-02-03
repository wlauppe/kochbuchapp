package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publicRecipeTag")
data class PublicRecipeTagDB(
    @PrimaryKey(autoGenerate = true) var id:Long,
    var recipeId:Long,
    var tag:String
)