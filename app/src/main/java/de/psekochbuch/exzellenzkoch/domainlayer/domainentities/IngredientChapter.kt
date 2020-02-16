package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

/**
 * The IngredientChapter class makes it possible to divide the recipe description into different chapters.
 * The particular chapters can be accessed through an unique identifyer. Each of them contains their share of the
 * recipe.
 * @param chapterId: An unique identifyer to access the ingredientchapter.
 * @param chapter: The name of the Chapter which will be displayed in the recipe.
 * @param ingredients: The List of ingredients wich are contained in this particular IngredientChapter
 */
class IngredientChapter(
    /**
     * Id of the chapters
     */
    val chapterId:Int,

    /**
     * Name of the chapter
     */
    var chapter:String = "",

    val ingredients:List<IngredientAmount>)
