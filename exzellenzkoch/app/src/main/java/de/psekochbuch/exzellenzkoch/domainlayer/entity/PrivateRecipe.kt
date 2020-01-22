package de.psekochbuch.exzellenzkoch.domainlayer.entity

import android.media.Image
import androidx.room.Entity

@Entity
class PrivateRecipe(ingredients:String,
                    image: Image,
                    title:String,
                    preparationDescription:String,
                    preparationTime:Int,
                    cookingTime:Int,
                    tags:MutableList<String>,
                    portions:Int) : Recipe(ingredients, image, title, preparationDescription, preparationTime, cookingTime, null, portions) {

    override var id: Int? = null

    override var ingredients:String? = null
     override var image:Image? = null

    override var title:String? = null

    override var preparationDescription:String? = null

    override var preparationTime:Int? = null

    override var cookingTime:Int? = null

    override var portions:Int? = null






    private fun isComplete():Boolean{
        return false //Todo
    }
    private fun convertToPublicRecipe(): PublicRecipe?{
        return null
    }



}