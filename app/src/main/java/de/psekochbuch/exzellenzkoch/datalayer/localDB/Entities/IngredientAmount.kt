package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredientAmount")
data class IngredientAmount(@PrimaryKey(autoGenerate = true) val id:Long,
                            val chapterId:Long,
                            val name:String,
                            val unit:String,
                            val amount:Int
                            )