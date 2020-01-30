package com.example.simplekochbuchapp.domainlayer.domainentities

import android.media.Image
import java.security.Timestamp

class PublicRecipe constructor(ingredients: List<IngredientChapter>, title:String,
                               rating:Double, preparation:String,
                               tags:List<String>, prepartionTime:Int,
                               cookingTime: Int, image: Image?, portions:Int ){



    val id: Int? = null
    var ingredientChapter: List<IngredientChapter> = ingredients
    var creationTimeStamp: Timestamp? = null
    var title:String? = title
    var rating: Double? = rating
    var preparation: String? = preparation
    var tags: List<String>? = tags
    var preparationTime: Int? = prepartionTime
    var cookingTime: Int? = cookingTime
    var image: Image? =image
    var portions: Int? = portions


}