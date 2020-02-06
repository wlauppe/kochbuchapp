package de.psekochbuch.exzellenzkoch.domainlayer.domainentities


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
