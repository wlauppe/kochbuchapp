package de.psekochbuch.exzellenzkoch.datalayer.Entity

import androidx.room.Embedded
import androidx.room.Relation

data class PublicRecipeWithIngredientChapter(
    @Embedded val publicRecipe: PublicRecipe,
    @Relation(
        parentColumn = "id",
        entityColumn = "recipeId"
    )
    val ingredientChapters: List<IngredientChapter>
)