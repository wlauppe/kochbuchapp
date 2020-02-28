package de.psekochbuch.exzellenzkoch.datalayer.remote.mapper

import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.IngredientChapterDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.IngredientDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.PublicRecipeDto
import de.psekochbuch.exzellenzkoch.datalayer.remote.dto.RecipeTagDto
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.Unit
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class PublicRecipeDtoEntityMapperTest{
    @Test
    fun testmapper(){
        val mapper = PublicRecipeDtoEntityMapper()

        val publicRecipe = PublicRecipe(1,
            "titel",
            "ingredientstext",
            listOf(IngredientChapter(2,"chapter2", listOf(IngredientAmount("ingredient",3.14159,"")))),
            listOf("tag1","tag2")
        )

        val convertedRecipe = mapper.toDto(publicRecipe)

        val reconvertedRecipe = mapper.toEntity(convertedRecipe)
    }

    @Test
    fun testnoparseableunit(){
        val mapper = PublicRecipeDtoEntityMapper()

        val publicRecipeDto = PublicRecipeDto(1,
            "titel",
            "ingredients",
            "prep",
            "hier/dort.bild",
            1,
            2,
            "KingKong",
            "2019-12-31 00:00:00",
            3,
            2.71828,
            listOf(IngredientChapterDto(1,"affe",listOf(IngredientDto(4,"fenster",1234.5,"sollnichtparseablesein!!!")))),
            listOf(RecipeTagDto("yumm"))
        )

        val convertedrecipe = mapper.toEntity(publicRecipeDto)

        assertEquals(convertedrecipe.ingredientChapter.last().ingredients.last().unit,"")
    }
}