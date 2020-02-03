package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "privateRecipeTag")
data class PrivateRecipeTagDB(
    @PrimaryKey(autoGenerate = true) var id:Long,
    var recipeId:Long,
    var tag:String
)