package de.psekochbuch.exzellenzkoch.datalayer.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.test.core.app.ApplicationProvider
import com.google.firebase.FirebaseApp
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeTagDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp.PrivateRecipeRepositoryImp
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthenticationResult
import de.psekochbuch.exzellenzkoch.datalayer.remote.service.AuthentificationImpl
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.*
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PublicRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class PublicRecipeRepositoryImpTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()


    companion object{
        lateinit var repo: PublicRecipeRepository
        lateinit var userRepo: UserRepository
        lateinit var recipe: PublicRecipe
        lateinit var errorRecipe: PublicRecipe
        lateinit var userId: String

        @BeforeClass
        @JvmStatic
        fun setup(){
            var authResult: AuthenticationResult? = null
            repo = PublicRecipeRepositoryImp.getInstance()
            userRepo = UserRepositoryImp.getInstance()
            FirebaseApp.initializeApp(ApplicationProvider.getApplicationContext())
            val latch = CountDownLatch(1)
            val randomStr = getRandomString(12)
            userId = "random${randomStr}"
            recipe = PublicRecipe(0,"titel","ingredients",
                listOf(IngredientChapter(2, "chapter",listOf(IngredientAmount("ingedient",2.0,"Einheit")))),
                listOf("tag1","tag2"),"So macht man es","",1,2, User(userId),
                Date(0),3,0.0)
            errorRecipe = PublicRecipe(0, "Error Fetching Recipe!", imgUrl = "file:///android_asset/exampleimages/error.png",creationTimeStamp = Date(0))
            AuthentificationImpl.register("extraTestServer${randomStr}@test.de","123456",userId){ a, b->
                authResult = b
                repo.setToken(a)
                userRepo.setToken(a)
                latch.countDown()
            }

            latch.await()

            assertEquals(authResult, AuthenticationResult.REGISTRATIONSUCCESS)
            runBlocking {
                val token = userRepo.addUser("random${randomStr}")
            }

        }

        @AfterClass
        @JvmStatic
        fun tearDown(){
            AuthentificationImpl.userDelete()
        }

        fun getRandomString(length: Int) : String {
            val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz"
            return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
        }
    }



    @Test
    fun uploadGetAndDeleteRecipe(){
        var id =0
        runBlocking { id = repo.publishRecipe(recipe) }

        val recipefromrepo = repo.getPublicRecipe(id).blockingObserve()!!


        assertEquals(recipefromrepo,recipe)

        runBlocking { repo.deleteRecipe(id) }

        val errorrecipefromrepo = repo.getPublicRecipe(id).blockingObserve()!!

        assertEquals(errorrecipefromrepo, errorRecipe)
    }

    @Test
    fun recipesfromuser(){
        var recipes = repo.getRecipesFromUser(userId).blockingObserve()!!

        var same: List<PublicRecipe> = listOf()

        assertEquals(recipes,same)

        var id =0
        runBlocking { id = repo.publishRecipe(recipe) }

        recipes = repo.getRecipesFromUser(userId).blockingObserve()!!

        assertEquals(recipes,listOf(recipe))

        runBlocking { repo.deleteRecipe(id) }
    }

    @Test
    fun search(){
        val recipe2 = PublicRecipe(0,"anders","ingredients",
            listOf(IngredientChapter(2, "chapter",listOf(IngredientAmount("ingedient",2.0,"Einheit")))),
            listOf("tag1","tag2"),"So macht man es","",1,2, User(userId),
            Date(0),3,0.0)

        var id1 = 0
        var id2 = 0
        runBlocking { id2 = repo.publishRecipe(recipe2) }
        runBlocking { id1 = repo.publishRecipe(recipe) }

        val search1 = repo.getPublicRecipes("anders",listOf(),listOf(),null,"titel").blockingObserve()

        assertEquals(search1, listOf(recipe2))

        val search2 = repo.getPublicRecipes("titel", listOf(),listOf(),null,"titel").blockingObserve()

        assertEquals(search2, listOf(recipe))

        runBlocking {
            repo.deleteRecipe(id1)
            repo.deleteRecipe(id2)}
    }

}

private fun <T> LiveData<T>.blockingObserve(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    observeForever{
        value = it
        latch.countDown()
    }

    latch.await()
    return value
}

private fun <T> List<T>.equals(list: List<T>): Boolean {
    if (this.size != list.size){
        return false
    } else{
        return this.zip(list).map { it.first!!.equals(it.second) }.foldRight(true,{a:Boolean,b:Boolean->a&&b})
    }
}
