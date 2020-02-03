package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publicRecipe")
data class PublicRecipeDB(
    @PrimaryKey(autoGenerate = true) var id:Long,
    var title:String,
    var preparationDescription:String,
    var cookingTime:Int,
    var preparationTime:Int,
    var userId:Long,
    var creationDate:Long,
    var portions:Int
)