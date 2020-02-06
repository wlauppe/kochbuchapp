package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*


class PublicRecipe (

    /**
     *Id of the recipe
     */
    val recipeId :Int,

    /**
     * Title of the recipe
     */
    val title:String = "",

    /**
     * All ingredients as a textline
     */
    val ingredientsText:String = "",

    val ingredientChapter: List<IngredientChapter> =listOf(),

    val tags:List<String> = listOf(),

    /**
     * Description how to create a meal
     */
    val preparation:String = "",

    /**
     * Picture of the meal
     */
    val imgUrl: String = "",

    /**
     * Time to cook a meal
     */
    val cookingTime:Int = 0,

    /**
     * Time to preparate a meal
     */
    val preparationTime:Int = 0,

    /**
     * The user who create the recipe
     */
    val user:User = User("Musterman"),

    /**
     * Date when the recipe was created
     */
    val creationTimeStamp:Date = Date(0),

    /**
     * Count of portions for people
     */
    val portions:Int = 0


)
{

    fun getDateAsLong() :Long
    {
        return creationTimeStamp.time
    }



    val id: Int? = null
}

//TODO  val creationTimeStamp kann ich das per Default  auf "now" setzten? JA das ist denke ich gut.
//TODO warum kann man ein Feld nicht tags nennen?, wäre besser als taglist"
//TODO für Timestamp die richtige Klasse nehmen, java.security ist definitv falsch.
