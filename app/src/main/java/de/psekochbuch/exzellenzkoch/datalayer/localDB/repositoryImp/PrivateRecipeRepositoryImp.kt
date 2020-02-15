package de.psekochbuch.exzellenzkoch.datalayer.localDB.repositoryImp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import de.psekochbuch.exzellenzkoch.datalayer.localDB.DB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.daos.PrivateRecipeTagDao
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeDB
import de.psekochbuch.exzellenzkoch.datalayer.localDB.entities.PrivateRecipeTagDB
import de.psekochbuch.exzellenzkoch.domainlayer.domainentities.PrivateRecipe
import de.psekochbuch.exzellenzkoch.domainlayer.interfaces.repository.PrivateRecipeRepository
import kotlinx.coroutines.Dispatchers
import java.util.*

/**
 * This class implements the access to the private recipe database
 */
class PrivateRecipeRepositoryImp(application: Application?): PrivateRecipeRepository {
    private val privateRecipeDao: PrivateRecipeDao? = DB.getDatabase(application!!)?.privateRecipeDao();
    private val privateRecipeTagDao: PrivateRecipeTagDao? = DB.getDatabase(application!!)?.privateRecipeTagDao();

    /**
     * This Method returns all private recipes
     * @return the recipes from DB wrapped in Livedata
     */
    override fun getPrivateRecipes(): LiveData<List<PrivateRecipe>> {
        val lData = liveData(Dispatchers.IO){
            try{
                val recipes = transformListPrivateRecipeDBToListPrivateRecipeDB(privateRecipeDao?.getAll()!!)
                emit(recipes)
            } catch (error : Throwable){
                emit(listOf())
            }
        }
        return lData
    }

    /**
     * This method returns a specific recipe
     * @param id: The id of the recipe
     * @return: returns the recipe with the id wrapped in Livedata
     */
    override fun getPrivateRecipe(id: Int): LiveData<PrivateRecipe> {
        val lData = liveData(Dispatchers.IO){
            try{
                val recipe = transformPrivateRecipeDBToPrivateRecipe(privateRecipeDao?.getRecipe(id.toLong())!!)
                emit(recipe)
            } catch (error : Throwable){
                emit(PrivateRecipe(0,"Konnte nicht geladen werden","",listOf(),"","file://android_assed/exampleimages/error.png",0,0,Date(System.currentTimeMillis()),0,0))
            }
        }
        return lData
    }


    /**
     * this method deletes a recipe with the id
     * @param id: The id of the recipe
     */
    override suspend fun deletePrivateRecipe(id: Int) {
        DB.databaseWriteExecutor.execute{
            privateRecipeDao?.deleteRecipe(id.toLong())
            privateRecipeTagDao?.deleteTagsFromRecipe(id.toLong())
        }

    }

    /**
     * this method inserts a private recipe to the DB. If the recipe has id 0, the DB
     * gives the recipe a unique identifier.
     * IF the id is not 0 and a private recipe exists in DB with that id, it will be overwritten
     * @param privateRecipe: The recipe to insert.
     */
    override suspend fun insertPrivateRecipe(privateRecipe: PrivateRecipe) {
        DB.databaseWriteExecutor.execute{
            val id = privateRecipeDao?.insert(transformPrivateRecipeToPrivateRecipeDB(privateRecipe))!!
            privateRecipeTagDao?.deleteTagsFromRecipe(id)
            for (tag: String in privateRecipe.tags){
                privateRecipeTagDao?.insert(PrivateRecipeTagDB(0,id,tag))
            }
        }
    }
    
    fun transformPrivateRecipeDBToPrivateRecipe(recipe:PrivateRecipeDB):PrivateRecipe{
        //können wir IDs auch als longs abspeichern?
        return PrivateRecipe(recipe.id.toInt(), recipe.title,recipe.ingredientsText,privateRecipeTagDao?.getTagsFromRecipe(recipe.id)!!.map{tag -> tag.tag},recipe.preparationDescription!!,recipe.imgURL,recipe.cookingTime,recipe.preparationTime, Date(recipe.creationDate),recipe.portions)
    }

    fun transformPrivateRecipeToPrivateRecipeDB(recipe:PrivateRecipe):PrivateRecipeDB{
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