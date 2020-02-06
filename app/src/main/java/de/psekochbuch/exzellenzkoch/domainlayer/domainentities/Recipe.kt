package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*

abstract class Recipe(
    /**
     *Id of the recipe
     */
    val recipeId: Int,

    /**
     * Title of the recipe
     */
    val title: String = "",

    /**
     * All ingredients as a textline
     */
    val ingredientsText: String = "",

    val tags: List<String> = listOf(),

    /**
     * Description how to create a meal
     */
    val preparation: String = "",

    /**
     * Picture of the meal
     */
    val imgUrl: String = "",

    /**
     * Time to cook a meal
     */
    val cookingTime: Int = 0,

    /**
     * Time to preparate a meal
     */
    val preparationTime: Int = 0,

    /**
     * Date when the recipe was created
     */
    val creationTimeStamp: Date = Date(System.currentTimeMillis()),

    /**
     * Count of portions for people
     */
    val portions: Int = 0

)


