package de.psekochbuch.exzellenzkoch.datalayer.repository

import de.psekochbuch.exzellenzkoch.datalayer.dto.PrivateRecipeTagDto
import de.psekochbuch.exzellenzkoch.datalayer.dto.SharedPrivateRecipeDto
import de.psekochbuch.exzellenzkoch.domainlayer.entity.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.entity.PrivateRecipeTag

interface PrivateRecipeRepository {

    fun shared_Private_Recipe_Dto_To_Private_Recipe(dto: SharedPrivateRecipeDto):PrivateRecipe

    fun private_Recipe_tag_Dto_To_Private_Recipe_Tag(dto: PrivateRecipeTagDto): PrivateRecipeTag
}