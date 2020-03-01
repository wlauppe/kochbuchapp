package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeTagDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeDB
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.*
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class PrivateRecipeRepositoryImpTest(){

    companion object{
        lateinit var repo:PrivateRecipeRepository
        val recipe = PrivateRecipe(1,"titel", "so mact man es", listOf("tag1","tag2"),"lalali","so",1,2,
            Date(),4,6)
        val errorrecipe = PrivateRecipe(0,"Konnte nicht geladen werden","",listOf(),"","file://android_assed/exampleimages/error.png",0,0,Date(0),0,0)

        val privateRecipeDao: PrivateRecipeDao = DB.getDatabase(ApplicationProvider.getApplicationContext()!!)?.privateRecipeDao()!!
        val privateRecipeTagDao: PrivateRecipeTagDao = DB.getDatabase(ApplicationProvider.getApplicationContext()!!)?.privateRecipeTagDao()!!

        @BeforeClass
        @JvmStatic
        fun setup(){
            repo = PrivateRecipeRepositoryImp.getInstance(ApplicationProvider.getApplicationContext())
            repo.deleteAll()
            Thread.sleep(1000)
        }
    }
    /**
     * Diese Regel sorgt dafür, dass der Test nicht auf einem Hintergrundthread ausgeführt
     * Ohne diese Regel kommen fehlermeldgunen, in der blocking observe methode, da in Livedata
     * definiert ist, dass der Observer sich nicht auf einem Hintergrundthread befinden darf
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @After
    fun setUp(){
        repo.deleteAll()
        Thread.sleep(1000)
    }

    @Test
    fun correctdelete(){
        runBlocking { repo.insertPrivateRecipe(recipe) }

        Thread.sleep(1000)

        runBlocking { repo.deletePrivateRecipe(1) }

        assertEquals(privateRecipeDao.getAll().size,0)
        assertEquals(privateRecipeTagDao.getAll().size,0)
    }

    @Test
    fun insertdeleteandget(){
        runBlocking { repo.insertPrivateRecipe(recipe)}

        val lfromDb = repo.getPrivateRecipe(1).blockingObserve()

        assertEquals(lfromDb,recipe)

        runBlocking { repo.deletePrivateRecipe(1)}

        val del = repo.getPrivateRecipe(1).blockingObserve()!!

        assertEquals(del, errorrecipe)
    }

    @Test
    fun insertandupdate(){
        runBlocking { repo.insertPrivateRecipe(recipe)}

        Thread.sleep(1000)

        val recipe2 = PrivateRecipe(1,"lalale", "efg", listOf("tag1","tag2"),"lalali","so",1,2,
            Date(),4,6)
        runBlocking { repo.insertPrivateRecipe(recipe2)}
        Thread.sleep(1000)

        val recipe = repo.getPrivateRecipe(1).blockingObserve()

        assertEquals(recipe,recipe2)
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