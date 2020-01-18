package de.psekochbuch.exzellenzkoch.domainlayer

import android.media.Image

open class Recipe constructor(ingredients:String,
                              image: Image,
                              title:String,
                              preparationDescription:String,
                              preparationTime:Int,
                              cookingTime:Int,
                              tags:MutableList<String>,
                              portions:Int){


    open var id: Int? = null

    open var ingredients:String? = ingredients

    open var image:Image? = image

    open var title:String? = title

    open var preparationDescription:String? = preparationDescription

    open var preparationTime:Int? = preparationTime

    open var cookingTime:Int? = cookingTime

    open var tags:MutableList<String>? = tags

    open var portions:Int? = portions


    // Functions



}
