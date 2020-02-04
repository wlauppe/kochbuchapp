package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import android.media.Image
import android.os.Parcel
import android.os.Parcelable
import java.security.Timestamp

class PublicRecipe constructor(ingredients: List<IngredientChapter>, title:String,
                               rating:Double, preparation:String,
                               tags:List<String>, preparationTime:Int,

                               cookingTime: Int, imgSrcUrl : String?, portions:Int ){

                               //cookingTime: Int, image: Image?, portions:Int ){


    var id: Int? = null
    var ingredientChapter: List<IngredientChapter> = ingredients
    var creationTimeStamp: Timestamp? = null
    var title:String? = title
    var rating: Double? = rating
    var preparation: String? = preparation
    var tags: List<String>? = tags
    var preparationTime: Int? = preparationTime
    var cookingTime: Int? = cookingTime
    var image: Image? = null
    var imgSrcUrl: String? = imgSrcUrl
    var portions: Int? = portions



}