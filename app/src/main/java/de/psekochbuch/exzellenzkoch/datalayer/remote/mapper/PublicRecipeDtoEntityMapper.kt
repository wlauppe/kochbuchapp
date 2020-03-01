package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import de.psekochbuch.exzellenzkoch.BuildConfig
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.IngredientChapterDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.IngredientDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.RecipeTagDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

//wandelt Recipes in Entities um

class PublicRecipeDtoEntityMapper() : EntityMapper<PublicRecipe,PublicRecipeDto>(){

    override fun toEntity(dto: PublicRecipeDto): PublicRecipe {

        return PublicRecipe(
            dto.id,
            dto.title,
            dto.ingredientsText,
            dto.ingredientsChapter.map {
                    chapter -> IngredientChapter(
                    chapter.id,
                    chapter.name,
                    chapter.ingredient.map{
                            ingredient -> try{IngredientAmount(ingredient.nameIngredient,ingredient.amount,ingredient.unit)}
                                catch (e:IllegalArgumentException){
                                    IngredientAmount(ingredient.nameIngredient,ingredient.amount,"")
                                }
                    })
            },
            dto.recipeTag.map{ tag -> tag.name},
            dto.preparationDescription,
            BuildConfig.IMG_PREFIX+dto.picture,
            dto.cookingTime,
            dto.preparationTime,
            User(dto.userId),
            convertStringToDate(dto.creationDate),
            dto.portions,
            dto.ratingAvg)
    }

    override fun toDto(entity: PublicRecipe): PublicRecipeDto {
        return PublicRecipeDto(
            entity.recipeId,
            entity.title,
            entity.ingredientsText,
            entity.preparation,
            entity.imgUrl.removePrefix(BuildConfig.IMG_PREFIX),
            entity.cookingTime,
            entity.preparationTime,
            entity.user.userId,
            convertDateToString(entity.creationTimeStamp),
            entity.portions,
            entity.avgRating,
            entity.ingredientChapter.map {
                    chapter -> IngredientChapterDto(
                        chapter.chapterId,
                        chapter.chapter,
                        chapter.ingredients.map {
                                ingredient -> IngredientDto(
                                    chapter.chapterId,
                                    ingredient.ingredient,
                                    ingredient.quantity,
                                    ingredient.unit)
                        }
                    )
            },
            entity.tags.map {
                    tag -> RecipeTagDto(tag)
            })
    }

    private fun convertStringToDate(date:String):Date
    {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)!!
    }

    private fun convertDateToString(date: Date):String
    {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(date)
    }
}