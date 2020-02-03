package de.psekochbuch.exzellenzkoch.domainlayer.domainentities


class PublicRecipe ( val title:String,
                     val ingredients: List<IngredientChapter>,
                     val creationTimeStamp : String,
                     val preparation:String = "",
                     val taglist:List<String>,
                     val preparationTime:Int = 0,
                     val cookingTime: Int = 0 ,
                     val imgUrl : String = "",
                     val portions:Int = 0) {


    val id: Int? = null
}

//TODO  val creationTimeStamp kann ich das per Default  auf "now" setzten?
//TODO warum kann man ein Feld nicht tags nennen?, wäre besser als taglist"
//TODO für Timestamp die richtige Klasse nehmen, java.security ist definitv falsch.