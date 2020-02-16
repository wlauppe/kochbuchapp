package de.psekochbuch.exzellenzkoch.domainlayer.domainentities

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class PrivateRecipeTest{
    @Test
    fun test(){
        val recipe = PrivateRecipe(1,"Bergsteiger",
            "#kapitel1  \n1-3Gramm rabarber\n2#kapitel2",listOf(),"prep","pics/NSFL",1,2, Date(),3
        )

        val con = recipe.convertToPublicRepipe(User("testuser"))

        1
    }
}