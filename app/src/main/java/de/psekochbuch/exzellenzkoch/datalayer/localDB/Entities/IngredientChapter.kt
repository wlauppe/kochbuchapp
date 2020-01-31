package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shoppingList")
data class IngredientChapter(
    @PrimaryKey(autoGenerate = true) var id:Long,
    var nameIngredient:String,
    var unit:String,
    var amount:Int
)
