package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeTagDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeDB
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import kotlinx.coroutines.Dispatchers
import java.util.*

class PrivateRecipeRepositoryImp(application: Application?): PrivateRecipeRepository {
    private val privateRecipeDao: PrivateRecipeDao? = DB.getDatabase(application!!)?.privateRecipeDao();
    private val privateRecipeTagDao: PrivateRecipeTagDao? = DB.getDatabase(application!!)?.privateRecipeTagDao();

    override fun getPrivateRecipes(): LiveData<List<PrivateRecipe>> {
        var recipes: List<PrivateRecipe> = listOf()
        val liveData = MutableLiveData<List<PrivateRecipe>>()
        DB.databaseWriteExecutor.execute{
            recipes = transformListPrivateRecipeDBToListPrivateRecipeDB(privateRecipeDao?.getAll()!!)
            liveData.postValue(recipes)
        }
        return liveData
    }

    override fun getPrivateRecipe(id: Int): LiveData<PrivateRecipe> {
        val lData = liveData(Dispatchers.IO, 0){
            emit(PrivateRecipe(3,"titelll", "so mact man es", listOf("tag1","tag2"),"lalali","so",1,2,
                Date(),4,6))
            try{
                var recipe = transformPrivateRecipeDBToPrivateRecipe(privateRecipeDao?.getRecipe(id.toLong())!!)
                emit(recipe)
            } catch (error : Throwable){
                emit(PrivateRecipe(0,"Konnte nicht geladen werden","",listOf(),"","file://android_assed/exampleimages/error.png",0,0,Date(System.currentTimeMillis()),0,0))
            }
        }
        return lData
    }
     /*
        //   var recipe: PrivateRecipe
        val liveData = MutableLiveData<PrivateRecipe>()
        DB.databaseWriteExecutor.execute{
            var recipedb = privateRecipeDao?.getRecipe(id.toLong())
            var recipe = transformPrivateRecipeDBToPrivateRecipe(recipedb!!)
            liveData.postValue(recipe)
        }
        return liveData
      */


    override suspend fun deletePrivateRecipe(id: Int) {
        DB.databaseWriteExecutor.execute{
            privateRecipeDao?.deleteRecipe(id.toLong())
            privateRecipeTagDao?.deleteTagsFromRecipe(id.toLong())
        }

    }

    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        DB.databaseWriteExecutor.execute{privateRecipeDao?.insert(transformPrivateRecipeToPrivateREcipeDB(privateRecipe))}
    }
    
    fun transformPrivateRecipeDBToPrivateRecipe(recipe:PrivateRecipeDB):PrivateRecipe{
        //können wir IDs auch als longs abspeichern?
        return PrivateRecipe(recipe.id.toInt(), recipe.title,recipe.ingredientsText,privateRecipeTagDao?.getTagsFromRecipe(recipe.id)!!.map{tag -> tag.tag},recipe.preparationDescription!!,recipe.imgURL,recipe.cookingTime,recipe.preparationTime, Date(recipe.creationDate),recipe.portions)
    }

    fun transformPrivateRecipeToPrivateREcipeDB(recipe:PrivateRecipe):PrivateRecipeDB{
        return PrivateRecipeDB(recipe.recipeId.toLong(),recipe.title,recipe.preparation,recipe.cookingTime,recipe.preparationTime,recipe.creationTimeStamp.time,recipe.portions,recipe.ingredientsText,recipe.imgUrl,recipe.publishedRecipeId)
    }

    fun transformListPrivateRecipeDBToListPrivateRecipeDB(recipes:List<PrivateRecipeDB>):List<PrivateRecipe>{
        return recipes.map(::transformPrivateRecipeDBToPrivateRecipe)
    }

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: PrivateRecipeRepository? = null

        fun getInstance(application:Application) =
            instance ?: synchronized(this) {
                instance ?: PrivateRecipeRepositoryImp(application).also { instance = it }
            }
    }
}