package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*


class PublicRecipe(
    var recipeId: Int=0,
    val title: String = "",
    val ingredientsText: String = "",
    val ingredientChapter: List<IngredientChapter> = listOf(),
    val tags: List<String> = listOf(),
    val preparation: String = "",
    var imgUrl: String = "",
    val cookingTime: Int = 0,
    val preparationTime: Int = 0,
    /**
     * The user who creates the recipe
     */
    val user:User = User("Musterman"),
    val creationTimeStamp: Date = Date(System.currentTimeMillis()),
    val portions: Int = 0,
    val avgRating : Double = 0.0
)
{
    /**
     * Convert Date in long
     */
    fun getDateAsLong() :Long
    {
        return creationTimeStamp.time
    }
}


//TODO warum kann man ein Feld nicht tags nennen?, wäre besser als taglist"
