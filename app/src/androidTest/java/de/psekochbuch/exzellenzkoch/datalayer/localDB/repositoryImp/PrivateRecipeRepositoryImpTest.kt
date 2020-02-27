package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeDB
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class PrivateRecipeRepositoryImpTest(){

    /**
     * Diese Regel sorgt dafür, dass der Test nicht auf einem Hintergrundthread ausgeführt
     * Ohne diese Regel kommen fehlermeldgunen, in der blocking observe methode, da in Livedata
     * definiert ist, dass der Observer sich nicht auf einem Hintergrundthread befinden darf
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertdeleteandget(){
        val repo = PrivateRecipeRepositoryImp.getInstance(ApplicationProvider.getApplicationContext())

        val recipe = PrivateRecipe(3,"titel", "so mact man es", listOf("tag1","tag2"),"lalali","so",1,2,
            Date(),4,6)

        runBlocking { repo.insertPrivateRecipe(recipe)}

        Thread.sleep(1000)

        val lfromDb = repo.getPrivateRecipe(3)

        assertEquals(lfromDb.blockingObserve()!!.ingredientsText,"so mact man es")

        runBlocking { repo.deletePrivateRecipe(3)}

        Thread.sleep(1000)

        val del = repo.getPrivateRecipe(3).blockingObserve()!!

        assertEquals(del.title,"Konnte nicht geladen werden")
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
        val recipe = repo.getPrivateRecipe(3).blockingObserve()
        assertEquals(recipe!!.title,"lalale")
    }
}

private fun <T> LiveData<T>.blockingObserve(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    observeForever{
        value = it
        latch.countDown()
    }

    latch.await(2, TimeUnit.SECONDS)
    return value
}