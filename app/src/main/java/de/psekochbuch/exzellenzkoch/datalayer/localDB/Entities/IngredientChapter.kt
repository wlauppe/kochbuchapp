package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class IngredientChapter(@PrimaryKey val id:Int,
                             val recipeId:Int,
                             val title:String,
                             @Ignore val ingredients:List<IngredientAmount>
                            )