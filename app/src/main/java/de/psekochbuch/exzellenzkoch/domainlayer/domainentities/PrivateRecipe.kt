package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.util.*

/**
 * The PrivateRecipe class contains all the information which is stored in a private Recipe.
 *@param recipeId: An unique identififier to access the particular private recipe
 *@param title: The name of the private Recipe.
 *@param ingredientsText: The String of the Ingredients in the following format: ingredient1,ingredient2,...
 *@param tags: a list of String, which represent the chosen tags for a specific private recipe
 * @param preparation: A textual description of the preparation of the private recipe.
 * 
 */
class PrivateRecipe(
    /**
     * unique identifier in local db
     */
    var recipeId: Int = 0,
    /**
     * title of the recipe
     */
    val title: String,
    /**
     * the ingredients of the recipe
     */
    val ingredientsText: String,
    /**
     * the tags of the recipe
     */
    val tags: List<String>,
    /**
     * description of preparation of the recipe
     */
    val preparation: String,
    /**
     * the path where the picture of the recipe is saved
     */
    var imgUrl: String,
    /**
     * the time the recipe needs after the preparation in minutes
     */
    val cookingTime: Int,
    /**
     * the time the recipe needs until preparation is done
     */
    val preparationTime: Int,
    /**
     * the timestamp, when the recipe was saved into database
     */
    val creationTimeStamp: Date,
    /**
     * number of portions, the recipe is constructed
     */
    val portions: Int,
    //das ist die Id des öffentlichen Rezepts unter dem das private Rezept veröffentlicht ist.
    //wenn das private Rezept noch nicht veröffentlicht wurde ist das 0
    /**
     * if a recipe was published, this is the identifier of the belonging public recipe
     * if the recipe was never published, it is 0
     */
    var publishedRecipeId : Int = 0
)  {

    /**
     * this method converts this to a Public recipe and throws an IllegalArgumentException, if not possible
     * @param user: The user, who wants to convert the recipe
     * @return: The public recipe object, with the information of this object
     */
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
        if (toParse.equals("")){
            return listOf()
        }
        var parsable = toParse
        if (toParse.toCharArray()[0] != '#'){
            parsable = "#Zutaten\n" + toParse
        }

        val toParseChapters = parsable.split("#").toList()
        try{
            return toParseChapters.subList(1,toParseChapters.size).map(::stringtochapter);
        } catch (e: Exception){
            throw java.lang.IllegalArgumentException("ingredients couldnt be parsed")
        }

    }

    fun stringtochapter(toParse: String): IngredientChapter{
        var ingredients = toParse.split("\n")
        ingredients = ingredients.filter { it != "" }
        return IngredientChapter(0, ingredients[0], ingredients.subList(1, ingredients.size).map(::stringtoingredient));
    }

    fun stringtoingredient(toParse: String): IngredientAmount {

        for (i in toParse.length downTo 1) {
            try {
                val number = getNumber(toParse.substring(0, i))
                val ingredientWithUnit = toParse.substring(i, toParse.length)
                val unit = ingredientWithUnit.split(" ")[0]
                val ingredient = ingredientWithUnit.dropWhile{!it.equals(' ')}.dropWhile{it.equals(' ')}
                try{
                    return IngredientAmount(ingredient, number, Unit.valueOf(unit))
                }catch(e:java.lang.IllegalArgumentException){
                    return IngredientAmount(ingredientWithUnit,number,Unit.KeineEinheit)
                }
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
            return (numbers[0].toLong() + numbers[1].toLong())/2.0
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
