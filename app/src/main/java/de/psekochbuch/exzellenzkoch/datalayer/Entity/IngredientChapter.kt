package de.psekochbuch.exzellenzkoch.datalayer.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientChapter(@PrimaryKey val id:Int,
                             val recipeId:Int,
                             val title:String)