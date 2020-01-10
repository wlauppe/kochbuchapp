package de.psekochbuch.exzellenzkoch.remote.entity

import android.media.Image

open class Recipe {
     open var id: Int? = null

    fun ingredients():String{
        return "" //TODO
    }

    fun image() {
        return //TODO
    }
    fun title():String{
        return "" //TODO
    }
    open fun preparation():Int{
        return 0 //TODO
    }
    open fun preparationTime():Int{
        return 0 //TODO
    }
    open fun cookingTime():Int{
        return 0 //TODO
    }
    open fun tags():List<String>{
        return emptyList() //TODO
    }
    open fun portions():Int{
        return 0 //TODO
    }
}