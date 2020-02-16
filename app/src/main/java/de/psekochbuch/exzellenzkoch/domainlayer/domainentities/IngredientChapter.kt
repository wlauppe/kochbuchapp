package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

/**
 * The IngredientChapter class contains information about parts of an entire recipe. The entire recipe is split up in
 * smaler parts, which are represented in IngredientChapters.
 * @param chapterId: an unique identifyer for the particular IngredientChapter
 * @param chapter: the name of the IngredientChapter, which will later be displayed as heading.
 * @param ingredients: a list of IngredientAmount which are contained in the particular IngredientChapter.
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

    /**
     * ingredients of the chapter
     */
    val ingredients:List<IngredientAmount>)
