package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import android.icu.text.CaseMap
import android.icu.text.TimeZoneFormat
import android.widget.ImageView
import java.sql.Timestamp

class PrivateRecipe (var title :String,
                     var creationDate:Timestamp,
                     var ingredients:List<IngredientAmount>,
                     var preparationDescription:String,
                     var cookingTime:Int,
                     var preparationTime:Int,
                     var image:String
                     ): Recipe() {

    var id: Int?  = null
    var tags: List<String> = emptyList()



}
