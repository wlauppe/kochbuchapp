package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import org.junit.Assert.*
import org.junit.Test

class IngredientAmountRepositoryTest{
    @Test
    fun ingredientAmountRepoIsCorrect()
    {
        val repo = IngredientAmountRepository(ApplicationProvider.getApplicationContext<Application>())

        repo.insert(IngredientAmount(1,2,"Kokosnuss","Flasche",24))


        //das repo macht das im hintergrund, deswegen sollte man warten
        Thread.sleep(1000)


        //achtung, dieser Test geht schief, wenn sich mehrere rezepte mit derselben recipeid in der db befinden
        assertEquals(repo.getIngredientAmountByRecipeId(2).last().name, "Kokosnuss")
    }
}