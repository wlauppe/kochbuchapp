package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class PrivateRecipeTest{
    @Test
    /**
     * This method tests if the parser converts the ingredients correctly
     */
    fun test(){
        val recipe = PrivateRecipe(1,"Bergsteiger",
            "2,3   Gramm käse#kapitel1#kapitel2  \n1-3 Gramm rabarber\n2#kapitel2\n 3/3 Gramm Frischkäse",
            listOf(),"prep","pics/NSFL",1,2, Date(),3)

        val con = recipe.convertToPublicRepipe(User("testuser"))

        assertEquals(con.ingredientChapter.size,4)
        assertEquals(con.ingredientChapter.last().chapter,"kapitel2")

        val ingredient = con.ingredientChapter.last().ingredients.last()
        assertEquals(ingredient.unit, Unit.Gramm)
        assertEquals(ingredient.ingredient,"Frischkäse")
    }
}