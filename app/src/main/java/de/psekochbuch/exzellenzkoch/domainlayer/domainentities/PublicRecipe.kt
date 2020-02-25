package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import java.lang.Exception
import java.util.*


class PublicRecipe(
    /**
     * identifier of the recipe on the server
     */
    var recipeId: Int=0,
    /**
     * the title of the recipe
     */
    val title: String = "",
    /**
     * the ingredients of the recipe as text
     */
    var ingredientsText: String = "",
    /**
     * the ingredients of the recipe as ingredientchapters datastructure
     */
    val ingredientChapter: List<IngredientChapter> = listOf(),
    /**
     * the tags belonging to the recipe
     */
    val tags: List<String> = listOf(),
    /**
     * the description of the preparation of the recipe
     */
    val preparation: String = "",
    /**
     * the url of the picture on the server of the recipe
     * TODO()
     */
    var imgUrl: String = "",
    /**
     * the time the recipe needs after the preparation in minutes
     */
    val cookingTime: Int = 0,
    /**
     * the time the recipe needs until preparation is done
     */
    val preparationTime: Int = 0,
    /**
     * the user who created the recipe
     */
    val user:User = User("Musterman"),
    /**
     * the time the recipe was published
     */
    val creationTimeStamp: Date = Date(System.currentTimeMillis()),
    /**
     * number of portions, the recipe is constructed
     */
    var portions: Int = 0,
    /**
     * average rating of the recipe
     */
    val avgRating : Double = 0.0
)
{
    /**
     * Convert Date in long
     */
    fun getDateAsLong() :Long
    {
        return creationTimeStamp.time
    }

    fun scale(newPortions: Int){
        var newtext = ""
        for (chapter:IngredientChapter in ingredientChapter){
            newtext += chapter.chapter + "\n"
            for (ingredient:IngredientAmount in chapter.ingredients) {
                ingredient.quantity = newPortions * ingredient.quantity / portions
                newtext += ingredient.quantity.toString() + " " + ingredient.unit + ingredient.ingredient + "\n"
            }
        }
        ingredientsText = newtext
        portions = newPortions
    }

    fun scaleUP(){
        scale(portions + 1)
    }
    fun scaleDown(){
        scale(portions - 1)
    }

    override fun equals(other: Any?): Boolean {
        val recipe: PublicRecipe
        try{
            recipe = other as PublicRecipe
        } catch (e: Exception){
            return false
        }

        val sameRecipeId = recipeId == recipe.recipeId
        val sameTitle = title.equals(recipe.title)
        val sameIngredientsText = ingredientsText.equals(recipe.ingredientsText)
        val sameNumberOfIngredientChapters = ingredientChapter.size == recipe.ingredientChapter.size
        val sameIngredientChapters = ingredientChapter.zip(recipe.ingredientChapter).map {
                it.first.chapter.equals(it.second.chapter) && //sameChapter
      //          it.first.chapterId == it.second.chapterId && //sameId
                it.first.ingredients.size == it.second.ingredients.size && //sameNumberOfIngredientAmount
                it.first.ingredients.zip(it.second.ingredients).map{
                    it.first.quantity == it.second.quantity && //sameQuantity
                            it.first.ingredient.equals(it.second.ingredient) && //sameIngredient
                            it.first.unit.equals(it.second.unit) //sameUnit
                }.foldRight(true,{a:Boolean,b:Boolean->a&&b}) //sameIngredientAmount
            }.foldRight(true,{a:Boolean,b:Boolean->a&&b})
        val sameNumberOfTags = tags.size == recipe.tags.size
        val sameTags = tags.zip(recipe.tags).map {it.first.equals(it.second)}.foldRight(true,{a:Boolean,b:Boolean->a&&b})
        val samePreparation = preparation.equals(recipe.preparation)
        val sameImgUrl = imgUrl.equals(recipe.imgUrl)
        val sameCookingTime = cookingTime == recipe.cookingTime
        val samePreparationTime = preparationTime == recipe.preparationTime
        val sameUser = user.description.equals(recipe.user.description) && user.imgUrl.equals(recipe.user.imgUrl) && user.userId.equals(recipe.user.userId)
        val sameCreationTimeStamp = getDateAsLong() == recipe.getDateAsLong()
        val samePortions = portions == recipe.portions
        val sameRating = avgRating == recipe.avgRating

        return recipeId == recipe.recipeId && //sameRecipeid
            title.equals(recipe.title) && //sameTitle
            ingredientsText.equals(recipe.ingredientsText) &&//sameIngredientsText
            ingredientChapter.size == recipe.ingredientChapter.size && //sameNumberOfIngredientChapters
            ingredientChapter.zip(recipe.ingredientChapter).map {
                it.first.chapter.equals(it.second.chapter) && //sameChapter
            //    it.first.chapterId == it.second.chapterId && //sameId
                it.first.ingredients.size == it.second.ingredients.size && //sameNumberOfIngredientAmount
                it.first.ingredients.zip(it.second.ingredients).map{
                    it.first.quantity == it.second.quantity && //sameQuantity
                    it.first.ingredient.equals(it.second.ingredient) && //sameIngredient
                    it.first.unit.equals(it.second.unit) //sameUnit
                }.foldRight(true,{a:Boolean,b:Boolean->a&&b}) //sameIngredientAmount
            }.foldRight(true,{a:Boolean,b:Boolean->a&&b}) && //sameIngredientChapter
            tags.size == recipe.tags.size && //sameNumberOfTags
            tags.zip(recipe.tags).map {it.first.equals(it.second)}.foldRight(true,{a:Boolean,b:Boolean->a&&b}) && //sameTags
            preparation.equals(recipe.preparation) && //samePreparation
            imgUrl.equals(recipe.imgUrl) && //sameImgUrl
            cookingTime == recipe.cookingTime && //sameCookingTime
            preparationTime == recipe.preparationTime && //samePreparationTime
            user.description.equals(recipe.user.description) && user.imgUrl.equals(recipe.user.imgUrl) && user.userId.equals(recipe.user.userId) && //sameUser
            getDateAsLong() == recipe.getDateAsLong() && //sameCreationTimeStamp
            portions == recipe.portions && //samePortions
            avgRating == recipe.avgRating //sameRating
    }
}