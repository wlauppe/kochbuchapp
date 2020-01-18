package de.psekochbuch.exzellenzkoch.domainlayer

import android.media.Image

open class Recipe {

    open var id: Int? = null

    open var ingredients:String? = null

    open var image:Image? = null

    open var title:String? = null

    open var preparationDescription:String? = null

    open var preparationTime:Int? = null

    open var cookingTime:Int? = null

    open var tags:MutableList<String>? = null

    open var portions:Int? = null


    // Functions



}
