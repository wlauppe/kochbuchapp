package de.psekochbuch.exzellenzkoch.datalayer.Entity

import androidx.room.Embedded
import androidx.room.Relation

data class IngredientChapterWithIngredientAmount(
    @Embedded val ingredientChapter: IngredientChapter,
    @Relation(
        parentColumn = "id",
        entityColumn = "chapterId"
    )
    val ingredientAmounts: List<IngredientAmount>
)