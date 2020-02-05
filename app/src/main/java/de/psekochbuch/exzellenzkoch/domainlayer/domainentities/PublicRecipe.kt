package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*


class PublicRecipe (

    /**
     *Id of the recipe
     */
    var recipeId :Int,

    /**
     * Title of the recipe
     */
    var title:String,

    /**
     * All ingredients as a textline
     */
    var ingredientsText:String,

    val ingredientChapter: List<IngredientChapter> =listOf(),

    val tags:List<String> = listOf(),

    /**
     * Description how to create a meal
     */
    var preparation:String,

    /**
     * Picture of the meal
     */
    var picture: String,

    /**
     * Time to cook a meal
     */
    var cookingTime:Int,

    /**
     * Time to preparate a meal
     */
    var preparationTime:Int,

    /**
     * The user who create the recipe
     */
    val user:User?,

    /**
     * Date when the recipe was created
     */
    var creationTimeStamp:Date,

    /**
     * Count of portions for people
     */
    var portions:Int


)
{

    fun getDateAsLong() :Long
    {
        return creationTimeStamp.time
    }



    val id: Int? = null
}

//TODO  val creationTimeStamp kann ich das per Default  auf "now" setzten?
//TODO warum kann man ein Feld nicht tags nennen?, wäre besser als taglist"
//TODO für Timestamp die richtige Klasse nehmen, java.security ist definitv falsch.