package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.IngredientChapterDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.IngredientDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.RecipeTagDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.*
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.Unit
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

//wandelt Recipes in Entities um

class PublicRecipeDtoEntityMapper() : EntityMapper<PublicRecipe,PublicRecipeDto>(){

    override fun toEntity(dto: PublicRecipeDto): PublicRecipe {
        return PublicRecipe(dto.id,dto.title,dto.ingredientsText,dto.ingredientChapter?.map {chapter -> IngredientChapter(chapter.id,chapter.name,chapter.ingredient?.map{ingredient -> IngredientAmount(ingredient.nameIngredient,ingredient.amount,Unit.valueOf(ingredient.unit))}!!)}!!,dto.recipeTag?.map{tag -> tag.name}!!,dto.preparationDescription,dto.picture,dto.cookingTime,dto.preparationTime, User(dto.userId!!),convertStringToDate(dto.creationDate),dto.portions,dto.ratingAvg)
    }

    override fun toDto(entity: PublicRecipe): PublicRecipeDto {
        return PublicRecipeDto(entity.recipeId,entity.title,entity.ingredientsText,entity.preparation,entity.imgUrl,entity.cookingTime,entity.preparationTime,entity.user.userId,convertDateToString(entity.creationTimeStamp),entity.portions,entity.avgRating,entity.ingredientChapter.map { chapter -> IngredientChapterDto(chapter.chapterId,chapter.chapter,chapter.ingredients.map { ingredient -> IngredientDto(chapter.chapterId,ingredient.ingredient,ingredient.quantity,ingredient.unit.getText())})},entity.tags.map {tag -> RecipeTagDto(tag)})
    }

    private fun convertStringToDate(date:String):Date
    {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)
    }

    private fun convertDateToString(date: Date):String
    {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(date)
    }
}


        /*
        var ingredients: IngredientAmount

        var title: String = dto.title
        var rating: Double =  dto.ratingAvg.toDouble()
        var preparation: String = dto.preparationDescription

        var tags: List<String> = listOf("")
        tags = getTagListFromDTO(dto.recipeTag)


        var prepartionTime: Int= dto.preperationTime
        var cookingTime: Int = dto.cookingTime
        var image: Image? = null //TODO
        var portions: Int = dto.portions


        publicRecipe = PublicRecipe(null, title, rating, preparation,
            tags, prepartionTime, cookingTime, image, portions)
*/
/*
    }
        val recipe = PublicRecipe(
            listOf(),
            "trockener Sandkuchen",
            5.0,
            "Backe backe Kuchen",
            listOf("trocken", "kuchen", "ungeniessbar"),
            5,
            2,
            null,
            5
        );

        return recipe
    }

         fun toDto(entity : PublicRecipe) : PublicRecipeDto {



             /*  val dto = PublicRecipeDto(
                 entity.id,
                 entity.title)
            */