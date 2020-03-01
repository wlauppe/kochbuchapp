package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.IngredientAmountDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.IngredientChapterDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PublicRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PublicRecipeTagDao
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.FavouriteRecipeRepository
import org.junit.*
import org.junit.Assert.*
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class FavouritRecipeRepositoryImpTest(){

    companion object{
        lateinit var repo: FavouriteRecipeRepository
        lateinit var ingredientAmountDao: IngredientAmountDao
        lateinit var ingredientChapterDao: IngredientChapterDao
        lateinit var publicRecipeTagDao: PublicRecipeTagDao
        lateinit var publicRecipeDao: PublicRecipeDao

        var recipe = PublicRecipe(1,"titel","ingredients",
            listOf(IngredientChapter(2, "chapter",listOf(IngredientAmount("ingedient",2.0,"Einheit")))),
            listOf("tag1","tag2"),"So macht man es")
        var errorRecipe = PublicRecipe(0,"ERROR",creationTimeStamp = Date(0))

        @BeforeClass @JvmStatic
        fun setup(){
            repo = FavouriteRecipeRepositoryImp.getInstance(ApplicationProvider.getApplicationContext())
            ingredientAmountDao = DB.getDatabase(ApplicationProvider.getApplicationContext())!!.ingredientAmountDao()!!
            ingredientChapterDao = DB.getDatabase(ApplicationProvider.getApplicationContext())!!.ingredientChapterDao()!!
            publicRecipeDao = DB.getDatabase(ApplicationProvider.getApplicationContext())!!.publicRecipeDao()!!
            publicRecipeTagDao = DB.getDatabase(ApplicationProvider.getApplicationContext())!!.publicRecipeTagDao()!!
            repo.deleteAll()
        }
    }
    /**
     * Diese Regel sorgt dafür, dass der Test nicht auf einem Hintergrundthread ausgeführt
     * Ohne diese Regel kommen fehlermeldgunen, in der blocking observe methode, da in Livedata
     * definiert ist, dass der Observer sich nicht auf einem Hintergrundthread befinden darf
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        repo.insertFavourite(recipe)
    }

    @After
    fun deleteFav(){
        repo.deleteAll()
        Thread.sleep(1000)

    }


    //der hier wird ignoriert, da synchronisationsprimoitve nur in repos sind und nicht in daos
    fun deleteAll(){
        repo.deleteAll()

        Thread.sleep(1000)

        assertEquals(ingredientAmountDao.getAll().size,0)
        assertEquals(ingredientChapterDao.getAll().size, 0)
        assertEquals(publicRecipeDao.getAll().size,0)
        assertEquals(publicRecipeTagDao.getAll().size,0)
    }

    @Test
    fun getfavourite(){
        val fromrepo = repo.getFavourite(1).blockingObserve()!!

        assertEquals(fromrepo, recipe)
    }

    @Test
    fun deletefavourite(){
        repo.removeFavourite(1)

        Thread.sleep(1000)

        val fromrepo = repo.getFavourite(1).blockingObserve()

        assertEquals(fromrepo,errorRecipe)
    }

    @Test
    fun deleteandgetall(){
        repo.deleteAll()

        Thread.sleep(1000)

        val fromrepo = repo.getFavourites().blockingObserve()!!

        assertEquals(fromrepo.size,0)
    }

    /**
     * This method inserts another recipe, so there are 2 recipes in the database. Then it deletes one of the recipes
     * and looks in the database if the recipe was deleted right
     */
    @Test
    fun safedelete(){
        var recipe2 = PublicRecipe(2,"titel2","ingredients2",
            listOf(IngredientChapter(2,"chapter2",listOf(IngredientAmount("ingedient2",4.0,"Einheit2"),
                IngredientAmount("ingredient",3.0,"Einheit3")
            ))),
            listOf("tag12","tag22"),"So macht man es2")

        repo.insertFavourite(recipe2)

        Thread.sleep(1000)

        repo.removeFavourite(1)

        Thread.sleep(1000)

        val fromrepo = repo.getFavourite(2).blockingObserve()

        assertEquals(fromrepo,recipe2)
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