package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class PrivateRecipeTest{
    @Test
    fun test(){
        val recipe = PrivateRecipe(1,"a","b",listOf(),"c","d",1,2, Date(),3)

        val converted = recipe.stringtochapters("#chapter1\n1.2 Bananen\n 3 / 12 kuba  #lalali \n 3,4 fsdjkl")
        assertEquals(converted,listOf(IngredientChapter(0,"chapter1",listOf(IngredientAmount("Bananen",1.2,Unit.KeineEinheit)))))
    }
}