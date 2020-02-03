package de.psekochbuch.exzellenzkoch.datalayer.localDB.Repositories

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.*
import org.junit.Assert.*
import org.junit.Test

class IngredientChapterRepositoryTest{
    @Test
    fun ingredientAmountRepoIsCorrect()
    {
        val chapterRepo = IngredientChapterRepository(ApplicationProvider.getApplicationContext<Application>())
        val ingredientRepo = IngredientAmountRepository(ApplicationProvider.getApplicationContext<Application>())

        chapterRepo.insert(IngredientChapter("ss",listOf(IngredientAmount("lalali",2.0,"lala"))))


        //das repo macht das im hintergrund, deswegen sollte man warten
        Thread.sleep(1000)


        //achtung, dieser Test geht schief, wenn sich mehrere rezepte mit derselben recipeid in der db befinden
        assertEquals(ingredientRepo.getIngredientAmountByIngredientChapterId(20).last().unit, "lala")
    }
}