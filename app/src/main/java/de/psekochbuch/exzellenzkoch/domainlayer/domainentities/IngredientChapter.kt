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

    /**
     * ingredients of the chapter
     */
    val ingredients:List<IngredientAmount>)
