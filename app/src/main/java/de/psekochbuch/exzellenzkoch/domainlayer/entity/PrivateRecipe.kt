package de.psekochbuch.exzellenzkoch.domainlayer.entity

import android.media.Image
import androidx.room.Entity

@Entity
class PrivateRecipe(ingredients:String,
                    image: Image?,
                    title:String,
                    preparationDescription:String,
                    preparationTime:Int,
                    cookingTime:Int,
                    tags:MutableList<String>,
                    portions:Int) : Recipe(ingredients, image, title, preparationDescription, preparationTime, cookingTime, null, portions) {

    override var id: Int? = null

    override var ingredients:String? = ingredients
     override var image:Image? = image

    override var title:String? = title

    override var preparationDescription: String? = preparationDescription

    override var preparationTime:Int? = preparationTime

    override var cookingTime:Int? = cookingTime

    override var portions:Int? = portions






    private fun isComplete():Boolean{
        return (id != null ||ingredients != ""|| image != null||title != ""||preparationDescription != ""
                ||preparationTime != null||cookingTime != null || portions != null)
    }
    private fun convertToPublicRecipe(): PublicRecipe?{
        return null
    }



}