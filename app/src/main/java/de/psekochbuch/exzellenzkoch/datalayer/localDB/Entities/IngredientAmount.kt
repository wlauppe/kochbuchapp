package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredientamount")
data class IngredientAmount(@PrimaryKey val id:Long,
                            val recipeId:Long,
                            val name:String,
                            val unit:String,
                            val amount:Int
                            )