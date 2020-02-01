package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*
import org.junit.Assert.*
import org.junit.Test

class ShoppingListRepositoryTest{
    @Test
    fun ingredientAmountRepoIsCorrect()
    {
        val repo = ShoppingListRepository(ApplicationProvider.getApplicationContext<Application>())

        repo.insert(ShoppingListDB(1,"Banana","unit",42))


        //das repo macht das im hintergrund, deswegen sollte man warten
        Thread.sleep(1000)

        assertEquals(repo.getById(1).nameIngredient, "Banana")
    }
}