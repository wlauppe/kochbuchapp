package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class PrivateRecipeRepositoryImpTest(){
    @Test
    fun insertdeleteandget(){
        val repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())

        val recipe = PrivateRecipe(3,"titel", "so mact man es", listOf("tag1","tag2"),"lalali","so",1,2,
            Date(),4,6)

        runBlocking { repo.insertPrivateRecipe(recipe)}

        Thread.sleep(1000)

        val lfromDb =   repo.getPrivateRecipe(3)

        Thread.sleep(1000)

        val rfromDb = lfromDb.value!!

        assertEquals(rfromDb.ingredientsText,"so mact man es")

        runBlocking { repo.deletePrivateRecipe(3)}

        Thread.sleep(1000)

        val del = repo.getPrivateRecipe(3)

        Thread.sleep(1000)

        assertEquals(del.value,null)
    }

    @Test
    fun insertandupdate(){
        val repo = PrivateRecipeRepositoryImp(ApplicationProvider.getApplicationContext())

        val recipe1 = PrivateRecipe(3,"abc", "efg", listOf("tag1","tag2"),"lalali","so",1,2,
            Date(),4,6)

        runBlocking { repo.insertPrivateRecipe(recipe1)}

        Thread.sleep(1000)

        val recipe2 = PrivateRecipe(3,"lalale", "efg", listOf("tag1","tag2"),"lalali","so",1,2,
            Date(),4,6)

        runBlocking { repo.insertPrivateRecipe(recipe2)}

        Thread.sleep(1000)

        val recipe = repo.getPrivateRecipe(3)

        Thread.sleep(1000)


        val realrecipe = recipe.value
        assertEquals(realrecipe!!.title,"lalale")
    }
}