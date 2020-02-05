package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "publicRecipe")
data class PublicRecipeDB(
    @PrimaryKey(autoGenerate = true) var id:Int,
    var title:String,
    var ingredientText:String,
    var preparationDescription:String,
    var picture:String,
    var cookingTime:Int,
    var preparationTime:Int,
    var userId:String?,
    var creationDate:Long,
    var portions:Int
)