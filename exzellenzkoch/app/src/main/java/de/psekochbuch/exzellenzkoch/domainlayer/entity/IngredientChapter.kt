package de.psekochbuch.exzellenzkoch.domainlayer.entity

import androidx.room.Embedded
import androidx.room.Entity

@Entity
class IngredientChapter {

    var chapter:String? = null
    @Embedded
    var ingredients:MutableList<IngredientAmount>? = null
}