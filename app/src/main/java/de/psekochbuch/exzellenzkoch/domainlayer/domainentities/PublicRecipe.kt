package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*


class PublicRecipe(
    /**
     * identifier of the recipe on the server
     */
    var recipeId: Int=0,
    /**
     * the title of the recipe
     */
    val title: String = "",
    /**
     * the ingredients of the recipe as text
     */
    val ingredientsText: String = "",
    /**
     * the ingredients of the recipe as ingredientchapters datastructure
     */
    val ingredientChapter: List<IngredientChapter> = listOf(),
    /**
     * the tags belonging to the recipe
     */
    val tags: List<String> = listOf(),
    /**
     * the description of the preparation of the recipe
     */
    val preparation: String = "",
    /**
     * the url of the picture on the server of the recipe
     * TODO()
     */
    var imgUrl: String = "",
    /**
     * the time the recipe needs after the preparation in minutes
     */
    val cookingTime: Int = 0,
    /**
     * the time the recipe needs until preparation is done
     */
    val preparationTime: Int = 0,
    /**
     * the user who created the recipe
     */
    val user:User = User("Musterman"),
    /**
     * the time the recipe was published
     */
    val creationTimeStamp: Date = Date(System.currentTimeMillis()),
    /**
     * number of portions, the recipe is constructed
     */
    val portions: Int = 0,
    /**
     * average rating of the recipe
     */
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
