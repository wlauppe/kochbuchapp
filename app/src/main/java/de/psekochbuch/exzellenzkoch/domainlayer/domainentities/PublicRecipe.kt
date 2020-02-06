package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*


class PublicRecipe(
    recipeId: Int,
    title: String = "",
    ingredientsText: String = "",
    val ingredientChapter: List<IngredientChapter> = listOf(),
    tags: List<String> = listOf(),
    preparation: String = "",
    imgUrl: String = "",
    cookingTime: Int = 0,
    preparationTime: Int = 0,
    /**
     * The user who create the recipe
     */
    val user:User = User("Musterman"),
    creationTimeStamp: Date = Date(System.currentTimeMillis()),
    portions: Int = 0,
    val avgRating : Double = 0.0
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
