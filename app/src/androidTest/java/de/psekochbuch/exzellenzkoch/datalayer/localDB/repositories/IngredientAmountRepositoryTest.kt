package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositories

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.*
import org.junit.Assert.*
import org.junit.Test

class IngredientAmountRepositoryTest{
    @Test
    fun ingredientAmountRepoIsCorrect()
    {
        val repo = IngredientAmountRepository(ApplicationProvider.getApplicationContext<Application>())

        repo.insert(IngredientAmount("Kokosnuss",2.0,"flasche"))


        //das repo macht das im hintergrund, deswegen sollte man warten
        Thread.sleep(1000)


        //achtung, dieser Test geht schief, wenn sich mehrere rezepte mit derselben recipeid in der db befinden
        assertEquals(repo.getIngredientAmountByIngredientChapterId(0).last().name, "Kokosnuss")
    }
}