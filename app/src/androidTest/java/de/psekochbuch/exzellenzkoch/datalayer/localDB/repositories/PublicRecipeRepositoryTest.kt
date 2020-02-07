package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositories

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.*
import org.junit.Assert.*
import org.junit.Test

class PublicRecipeRepositoryTest{
    @Test
    fun ingredientAmountRepoIsCorrect()
    {
        val repo = PublicRecipeRepository(ApplicationProvider.getApplicationContext<Application>())

        repo.insert(PublicRecipe(listOf(),"titel",2.0,"prep",listOf("fag1"),2,2,null,3))


        //das repo macht das im hintergrund, deswegen sollte man warten
        Thread.sleep(1000)


        //achtung, dieser Test geht schief, wenn sich mehrere rezepte mit derselben recipeid in der db befinden
        assertEquals(repo.getAll().last().tags?.last(), "fag1")
    }
}