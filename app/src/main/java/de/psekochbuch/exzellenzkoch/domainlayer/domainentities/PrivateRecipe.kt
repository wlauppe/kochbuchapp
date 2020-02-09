package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*

class PrivateRecipe(
    recipeId: Int = 0,
    title: String,
    ingredientsText: String,
    tags: List<String>,
    preparation: String,
    imgUrl: String,
    cookingTime: Int,
    preparationTime: Int,
    creationTimeStamp: Date,
    portions: Int
) : Recipe(
    recipeId,
    title,
    ingredientsText,
    tags,
    preparation,
    imgUrl,
    cookingTime,
    preparationTime,
    creationTimeStamp,
    portions
) {

    fun convertToPublicRepipe() : PublicRecipe
    {
        return PublicRecipe(0, title, ingredientsText, stringtochapters(ingredientsText),tags, preparation, imgUrl, cookingTime, preparationTime, User("Max Mustermann"),Date(System.currentTimeMillis()))
    }

    fun stringtochapters(toParse: String): List<IngredientChapter>{
        val toParseChapters = toParse.split("#").toList()
        return toParseChapters.map(::stringtochapter);
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
            break
        }
        throw IllegalArgumentException()
    }

    //testet wie der String toParse getrennt ist und berechnet dann den wert
    fun getNumber(toParse: String): Double {
        val withoutWS = toParse.replace(" ", "")
        if (zahlgetrennt(withoutWS, ".")) return withoutWS.toDouble()
        if (zahlgetrennt(withoutWS, ",")) return withoutWS.replace(',', '.').toDouble()
        if (zahlgetrennt(withoutWS, "/")) {
            val numbers = withoutWS.split("/").toTypedArray()
            return numbers[0].toLong() / numbers[1].toLong().toDouble()
        }
        throw IllegalArgumentException()
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
