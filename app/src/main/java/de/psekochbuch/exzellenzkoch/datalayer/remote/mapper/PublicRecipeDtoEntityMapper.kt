package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe

//wandelt Recipes in Entities um

class PublicRecipeDtoEntityMapper() {
    fun toEntity(dto : PublicRecipeDto) : PublicRecipe {
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

        /* fun toDto(entity : PublicRecipe) : PublicRecipeDto {

            val dto = PublicRecipeDto(
                 entity.id,
                 entity.title,
                5.0,
                "Backe backe Kuchen",
                listOf("trocken", "kuchen", "ungeniessbar"),
                5,
                2,
                null,
                5
            );

            */


}


