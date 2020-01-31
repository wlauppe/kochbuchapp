package de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities


import android.media.Image
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//habe ingredientstext weggelassen, weil das ja das gleiche ist wie die ingredientchapter datenstruktur was zu inkonsistenzen führen könnte

@Entity
data class PublicRecipe(@PrimaryKey val id:Int,
                        val title:String,
                        val prepatation:String,
                    //  val pic:Image,
                        val cookingTime:Int,
                        val preparationTime:Int,
                        val userId:String,
                        val creationDate:Long,
                        val portions:Int,
                        @Ignore val ingredientChapters:List<IngredientChapter>
                        )