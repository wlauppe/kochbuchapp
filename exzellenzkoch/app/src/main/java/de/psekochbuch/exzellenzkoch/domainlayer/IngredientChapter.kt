package de.psekochbuch.exzellenzkoch.domainlayer

import androidx.room.Entity
import de.psekochbuch.exzellenzkoch.domainlayer.IngredientAmount

@Entity
class IngredientChapter {

    var chapter:String? = null

    var ingredients:MutableList<IngredientAmount>? = null
}