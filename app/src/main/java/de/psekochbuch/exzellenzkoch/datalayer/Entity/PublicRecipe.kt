package de.psekochbuch.exzellenzkoch.datalayer.Entity

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PublicRecipe(@PrimaryKey val id: Int,
                        val title: String,
                        val ingreientsText:String,
                        val preparationDescription: String,
               //       val pic: Image,  (Ich weiß nicht ob man das so mit room machen kann
                        val cookingTime:Int,
                        val preparationTime:Int,
                        val userId:String,
                        val creationDate:Long,
                        val portions:Int)