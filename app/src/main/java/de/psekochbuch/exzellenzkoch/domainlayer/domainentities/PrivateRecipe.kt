package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*

class PrivateRecipe(
    var recipeId: Int = 0,
    val title: String,
    val ingredientsText: String,
    val tags: List<String>,
    val preparation: String,
    var imgUrl: String,
    val cookingTime: Int,
    val preparationTime: Int,
    val creationTimeStamp: Date,
    val portions: Int,
    //das ist die Id des öffentlichen Rezepts unter dem das private Rezept veröffentlicht ist.
    //wenn das private Rezept noch nicht veröffentlicht wurde ist das 0
    var publishedRecipeId : Int = 0
)  {

    fun convertToPublicRepipe(user:User) : PublicRecipe
    {
        return PublicRecipe(
            publishedRecipeId,
            title, 
            ingredientsText,
            stringtochapters(ingredientsText),
            tags,
            preparation,
            imgUrl,
            cookingTime,
            preparationTime,
            user,
            Date(System.currentTimeMillis()),
            portions,
            0.0)
    }

    fun stringtochapters(toParse: String): List<IngredientChapter>{
        val toParseChapters = toParse.split("#").toList()
        return toParseChapters.subList(1,toParseChapters.size).map(::stringtochapter);
    }

    fun stringtochapter(toParse: String): IngredientChapter{
        val ingredients = toParse.split("\n")
        return IngredientChapter(0, ingredients[0], ingredients.subList(1, ingredients.size).map(::stringtoingredient));
    }

    fun stringtoingredient(toParse: String): IngredientAmount {
        for (i in toParse.length downTo 1) {
            try {
                val number = getNumber(toParse.substring(0, i))
                return IngredientAmount(toParse.substring(i, toParse.length), number, Unit.KeineEinheit)
            } catch (e: IllegalArgumentException) {
                continue
            }
        }
        throw IllegalArgumentException()
    }

    //testet wie der String toParse getrennt ist und berechnet dann den wert
    fun getNumber(toParse: String): Double {
        val withoutWS = toParse.replace(" ", "")
        if (zahlgetrennt(withoutWS, "-")) {
            val numbers = withoutWS.split("-")
            return (getNumber(numbers[0]) + getNumber(numbers[1]))/2
        }
        if (zahlgetrennt(withoutWS, ".")) return withoutWS.toDouble()
        if (zahlgetrennt(withoutWS, ",")) return withoutWS.replace(',', '.').toDouble()
        if (zahlgetrennt(withoutWS, "/")) {
            val numbers = withoutWS.split("/").toTypedArray()
            return numbers[0].toLong() / numbers[1].toLong().toDouble()
        }
        return toParse.toDouble()
    }

    // gibt ja zurück wenn der String toParse von der form (0|..|9)+(trenner)(0|..|9)+
    fun zahlgetrennt(toParse: String, trenner: String): Boolean {
        val geteilt = toParse.split(trenner).toTypedArray()
        if (geteilt.size != 2) return false
        try {
            geteilt[0].toLong()
            geteilt[1].toLong()
        } catch (n: NumberFormatException) {
            return false
        }
        return true
    }
}
