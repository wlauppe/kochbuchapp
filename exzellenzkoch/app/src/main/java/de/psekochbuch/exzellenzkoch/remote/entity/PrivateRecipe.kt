package de.psekochbuch.exzellenzkoch.remote.entity

import android.icu.text.CaseMap
import android.media.Image

class PrivateRecipe : Recipe() {

     override var id: Int? = null


    fun getIngredients():String{
        return "" //TODO
    }
    fun setIngredients(ingredients:String){
        //TODO
    }
    fun setImage(image:Image){
        //TODO
    }
    fun getImage():Image?{
        return null //TODO
    }

    fun setTitle(title: String){
        //TODO
    }
    fun getTitle():String{
        return "" //TODO
    }
    override fun preparation():Int{
        return 0 //TODO
    }
    override fun preparationTime():Int{
        return 0 //TODO
    }
    override fun cookingTime():Int{
        return 0 //TODO
    }
    override fun tags():List<String>{
        return emptyList() //TODO
    }
    override fun portions():Int{
        return 0 //TODO
    }

    private fun isComplete():Boolean{
        return false //Todo
    }
    private fun convertToPublicRecipe():PublicRecipe?{
        return null
    }
}