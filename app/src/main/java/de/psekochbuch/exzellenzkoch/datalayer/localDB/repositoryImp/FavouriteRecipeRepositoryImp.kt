package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.*
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.IngredientAmountDB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.IngredientChapterDB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PublicRecipeDB
import de.psekochbuch.exzellenzkoch.datalayer.remote.repository.UserRepositoryImp
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientAmount
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.IngredientChapter
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PublicRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.User
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.UserRepository
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.FavouriteRecipeRepository
import kotlinx.coroutines.Dispatchers
import java.util.*

class FavouriteRecipeRepositoryImp(application: Application?) : FavouriteRecipeRepository{
    private val publicRecipeDao: PublicRecipeDao = DB.getDatabase(application!!)?.publicRecipeDao()!!
    private val publicRecipeTagDao: PublicRecipeTagDao = DB.getDatabase(application!!)?.publicRecipeTagDao()!!
    private val ingredientChapterDao: IngredientChapterDao = DB.getDatabase(application!!)?.ingredientChapterDao()!!
    private val ingredientAmountDao: IngredientAmountDao = DB.getDatabase(application!!)?.ingredientAmountDao()!!


    override fun getFavourites(): LiveData<List<PublicRecipe>> {
        val lData = liveData(Dispatchers.IO){
            try{
                val recipes = transformPublicRecipeDBListToPublicRecipeList(publicRecipeDao.getAll())
                emit(recipes)
            } catch (error : Throwable){
                emit(listOf())
            }
        }
        return lData
    }

    override fun getFavourite(id: Int): LiveData<PublicRecipe> {
        val lData = liveData(Dispatchers.IO){
            try{
                val recipe = transformPublicRecipeDBToPublicRecipe(publicRecipeDao.getRecipe(id.toLong()))
                emit(recipe)
            } catch (error : Throwable){
                emit(PublicRecipe(0,"ERROR"))
            }
        }
        return lData
    }

    override fun removeFavourite(id: Int) {
        DB.databaseWriteExecutor.execute{
            publicRecipeDao.deleteRecipe(id.toLong())
            ingredientChapterDao.deleteChaptersFromRecipe(id.toLong())
        }
    }

    override fun insertFavourite(recipe: PublicRecipe) {
        DB.databaseWriteExecutor.execute{
            val recipeId = publicRecipeDao.insert(transformPublicRecipeToPublicRecipeDB(recipe))
            for (chapter: IngredientChapter in recipe.ingredientChapter){
                val chapterId = ingredientChapterDao.insert(IngredientChapterDB(0,recipeId,chapter.chapter))
                for (ingredient: IngredientAmount in chapter.ingredients){
                    ingredientAmountDao.insert(IngredientAmountDB(0,chapterId,ingredient.ingredient,ingredient.unit,ingredient.quantity))
                }
            }
        }
    }

    override fun deleteAll(){
        DB.databaseWriteExecutor.execute{
            publicRecipeDao.deleteAll()
            publicRecipeTagDao.deleteAll()
            ingredientAmountDao.deleteAll()
            ingredientChapterDao.deleteAll()
        }
    }

    fun transformPublicRecipeToPublicRecipeDB(publicRecipe:PublicRecipe): PublicRecipeDB{
        return PublicRecipeDB(publicRecipe.recipeId,
            publicRecipe.title,
            publicRecipe.ingredientsText,
            publicRecipe.preparation,
            publicRecipe.cookingTime,
            publicRecipe.preparationTime,
            publicRecipe.user.userId,
            publicRecipe.getDateAsLong(),
            publicRecipe.portions,
            publicRecipe.imgUrl)
    }

    fun transformPublicRecipeListToPublicRecipeDBList(recipes:List<PublicRecipe>): List<PublicRecipeDB>{
        return recipes.map(::transformPublicRecipeToPublicRecipeDB)
    }

    fun transformPublicRecipeDBToPublicRecipe(publicRecipeDB: PublicRecipeDB): PublicRecipe{
        val tags = publicRecipeTagDao.getTagsFromRecipe(publicRecipeDB.id.toLong()).map {
            it.tag
        }
        val chapters = ingredientChapterDao.getIngredientChapterByRecipeId(publicRecipeDB.id).map{
            val ingredientAmounts = ingredientAmountDao.getIngredientAmountByIngredientChapterId(it.id.toLong()).map {
                IngredientAmount(it.name,it.amount,it.unit)
            }
            IngredientChapter(it.id.toInt(),it.title,ingredientAmounts)
        }
        return PublicRecipe(publicRecipeDB.id,
            publicRecipeDB.title,
            publicRecipeDB.ingredientText,
            chapters,
            tags,
            publicRecipeDB.preparationDescription,
            publicRecipeDB.imageUrl,
            publicRecipeDB.cookingTime,
            publicRecipeDB.preparationTime,
            User(publicRecipeDB.userId),
            Date(publicRecipeDB.creationDate),
            publicRecipeDB.portions,
            0.0
            )
    }

    fun transformPublicRecipeDBListToPublicRecipeList(recipes: List<PublicRecipeDB>):List<PublicRecipe>{
        return recipes.map(::transformPublicRecipeDBToPublicRecipe)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: FavouritRecipeRepository? = null

        fun getInstance(application:Application) =
            instance ?: synchronized(this) {
                instance ?: FavouritRecipeRepositoryImp(application).also { instance = it }
            }
    }
}