package de.psekochbuch.exzellenzkoch.remote.entity

import android.media.Image
import java.sql.Timestamp

class PublicRecipe : Recipe() {
   override var id: Int? = null

    fun getIngredients():String{
        return "" //TODO
    }
    fun getCreationTimeStamp():Timestamp?{
        return null//TODO
    }

    fun getTitle():String{
        return "" //TODO
    }
    fun getRating():Double?{
        return null //TODO
    }
    fun getPreparation():String{
        return "" //TODO
    }
     fun getTags():List<String>{
        return emptyList() //TODO
    }
    fun getPreparationTime():Int{
        return 0 //TODO
    }
    fun getCookingTime():Int{
        return 0 //TODO
    }
    fun getImage():Image?{
        return null //TODO
    }
    fun getComments():List<Comment>{
        return emptyList() //TODO
    }
    fun getPortions():Int{
        return 0 //TODO
    }

    fun report(){
        //TODO
    }


}
