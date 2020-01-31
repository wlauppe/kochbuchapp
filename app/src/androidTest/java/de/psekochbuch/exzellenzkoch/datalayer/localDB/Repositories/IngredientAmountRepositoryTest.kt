package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import org.junit.Assert.*
import org.junit.Test
import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.datalayer.localDB.Entities.*

class IngredientAmountRepositoryTest{
    @Test
    fun ingredientAmountRepoIsCorrect()
    {
        val repo = IngredientAmountRepository(ApplicationProvider.getApplicationContext<Application>())

        repo.insert(IngredientAmount(1,2,"Banana","Afrika",42))

        assertEquals(repo.getIngredientAmountFromIngredientChapter(2).name, "Banana")
    }
}