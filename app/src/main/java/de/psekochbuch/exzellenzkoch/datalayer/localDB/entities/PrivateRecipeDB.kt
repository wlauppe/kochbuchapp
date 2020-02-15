package de.psekochbuch.exzellenzkoch.datalayer.localDB.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "privateRecipe")
data class PrivateRecipeDB(
    @PrimaryKey(autoGenerate = true) var id:Long,
    var title:String,
    var preparationDescription:String,
    var cookingTime:Int,
    var preparationTime:Int,
    var creationDate:Long,
    var portions:Int,
    var ingredientsText:String,
    //kein Bild wird durch einen leeren String repräsentiert
    var imgURL:String,
    var publishedID:Int
)