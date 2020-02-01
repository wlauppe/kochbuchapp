package de.psekochbuch.exzellenzkoch.datalayer.remote.dto

import android.media.Image
import java.util.*

class SharedPrivateRecipeDto constructor(recipeId:Int,
                                         title: String,
                                         ingredientText:String,
                                         preparationDescription:String,
                                         picture:Image?,
                                         cookingTime:Int,
                                         preparationTime:Int,
                                         userId:String,
                                         creationDate:Date?,
                                         portions:Int,
                                         tags:MutableList<PrivateRecipeTagDto>){
    var recipeId:Int = recipeId
    var title: String = title
    var ingredientText:String = ingredientText
    var preparationDescription:String = preparationDescription
    var picture:Image? = picture
    var cookingTime:Int = cookingTime
    var preparationTime:Int = preparationTime
    var userId:String = userId
    var creationDate:Date? = creationDate
    var portions:Int = portions
    var tags:MutableList<PrivateRecipeTagDto> = tags

}
