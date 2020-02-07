package de.psekochbuch.exzellenzkoch.datalayer.localDB.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredientamount")
data class IngredientAmountDB(@PrimaryKey val id:Long,
                            val chapterId:Long,
                            val name:String,
                            val unit:String,
                            val amount:Double
                            )