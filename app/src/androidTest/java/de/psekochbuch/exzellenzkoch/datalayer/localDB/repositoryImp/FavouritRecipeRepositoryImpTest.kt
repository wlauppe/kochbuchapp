package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.FavouriteRecipeRepository
import org.junit.*
import org.junit.Assert.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class FavouritRecipeRepositoryImpTest(){

    /**
     * Diese Regel sorgt dafür, dass der Test nicht auf einem Hintergrundthread ausgeführt
     * Ohne diese Regel kommen fehlermeldgunen, in der blocking observe methode, da in Livedata
     * definiert ist, dass der Observer sich nicht auf einem Hintergrundthread befinden darf
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    var repo: FavouriteRecipeRepository = FavouriteRecipeRepositoryImp.getInstance(ApplicationProvider.getApplicationContext())

    var recipe = PublicRecipe(1,"titel","ingredients",
        listOf(IngredientChapter(2,"chapter",listOf(IngredientAmount("ingedient",2.0,"Einheit")))))



    @Before
    fun setUp(){
        repo.insertFavourite(recipe)
        Thread.sleep(1000)
    }

    @After
    fun deleteFav(){
        repo.removeFavourite(1)
        Thread.sleep(1000)
    }

    @Test
    fun getfavourite(){
        val fromrepo = repo.getFavourite(1).blockingObserve()!!

        assertEquals(fromrepo.title,"titel")
    }

    @Test
    fun deletefavourite(){
        repo.removeFavourite(1)

        Thread.sleep(1000)

        val fromrepo = repo.getFavourite(1).blockingObserve()

        assertEquals(fromrepo!!.title,"ERROR")
    }

    @Test
    fun deleteandgetall(){
        repo.deleteAll()

        Thread.sleep(1000)

        val fromrepo = repo.getFavourites().blockingObserve()!!

        assertEquals(fromrepo.size,0)
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