package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import android.icu.text.CaseMap
import android.icu.text.TimeZoneFormat
import android.widget.ImageView
import java.sql.Timestamp
import java.util.*

class PrivateRecipe(
    recipeId: Int,
    title: String,
    ingredientsText: String,
    tags: List<String>,
    preparation: String,
    imgUrl: String,
    cookingTime: Int,
    preparationTime: Int,
    creationTimeStamp: Date,
    portions: Int
) : Recipe(
    recipeId,
    title,
    ingredientsText,
    tags,
    preparation,
    imgUrl,
    cookingTime,
    preparationTime,
    creationTimeStamp,
    portions
) {

    //var id: Int?  = null
    //Thomas: var id:Int = 0 macht sinn, denn die DB weist wenn die id 0 ist dem rezept eine generierte id zu



}
