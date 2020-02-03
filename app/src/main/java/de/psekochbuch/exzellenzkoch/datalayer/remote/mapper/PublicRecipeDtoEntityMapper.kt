package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

//wandelt Recipes in Entities um

class PublicRecipeDtoEntityMapper() {
    //TODO Implement
    //fun toEntity(dto: PublicRecipeDto): PublicRecipe


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