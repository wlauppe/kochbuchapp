package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "ingredientChapter")
data class IngredientChapterDB(
    @PrimaryKey(autoGenerate = true) var id:Long,
    var recipeId:Long,
    var title:String
)
